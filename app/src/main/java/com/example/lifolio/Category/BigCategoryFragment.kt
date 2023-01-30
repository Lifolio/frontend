package com.example.lifolio.Category

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.lifolio.Category.model.PostCategoryReq
import com.example.lifolio.JWT.ApiClient
import com.example.lifolio.MainApplication
import com.example.lifolio.R
import com.example.lifolio.databinding.FragmentBigCategoryBinding
import com.example.lifolio.util.model.BaseRes
import com.example.lifolio.util.model.MethodCallback
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.listener.ColorListener
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.github.dhaval2404.colorpicker.util.ColorUtil.parseColor
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext
import kotlin.math.roundToInt


class BigCategoryFragment : Fragment() {
    private lateinit var binding : FragmentBigCategoryBinding
    private lateinit var addCategoryActivity: AddCategoryActivity

    var subCategoryNameList: ArrayList<String> = arrayListOf()
    var colorIdSelected: Int = 0
    var categoryName: String = ""

    companion object {
        const val TAG : String = "BIG_CATEGORY_FRAGMENT"

        fun newInstance() : BigCategoryFragment {
            return BigCategoryFragment()
        }

        val CATEGORY_COLOR_MAP : HashMap<String, Int> = hashMapOf(
            "#FF4C34" to 1,
            "#FF4C35" to 2,
            "#FFAD3F" to 3,
            "#FFDC11" to 4,
            "#B7F47B" to 5,
            "#7EC636" to 6,
            "#89C8FE" to 7,
            "#007AFF" to 8,
            "#BE88E8" to 9,
            "#FF9796" to 11,
            "#FAE5D4" to 12
        )

        val CATEGORY_COLOR_LIST = CATEGORY_COLOR_MAP.keys.toList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        addCategoryActivity = context as AddCategoryActivity // Context를 부모 액티비티로 형변환해서 할당, context 필요시 사용
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBigCategoryBinding.inflate(layoutInflater)

        binding.bigCategorySubmitBtn.isEnabled = false

        // 카테고리명 칸, 대표색상 채워지면 버튼 활성화
        binding.bigCategoryNameEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                categoryName = binding.bigCategoryNameEt.text.toString()
                binding.bigCategorySubmitBtn.isEnabled = (categoryName != "" && colorIdSelected != 0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        // EditText 값 입력 시 Chip 생성
        binding.bigCategoryChipEt.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val et = v as EditText
                val name = et.text.toString()
                subCategoryNameList.add(name)
                binding.bigCategoryFlexboxlt.addChip(name)

                et.text = null
            }
            return@setOnKeyListener false
        }

        // ColorPicker Dialog 띄우기
        binding.materialDialogPickerCircleBtn.setOnClickListener() {
            MaterialColorPickerDialog
                .Builder(addCategoryActivity)
                .setTitle("카테고리 색상 선택")
                .setColorShape(ColorShape.CIRCLE)
                .setColorSwatch(ColorSwatch._300)
                .setDefaultColor(parseColor("#ffffff"))
                .setColors(CATEGORY_COLOR_LIST)
                .setTickColorPerCard(true)
                .setColorListener (object : ColorListener {   // Dialog 확인을 누르면, Color ID 혹은 Hex String 값 반환. colorHex 쓰면 됨
                    override fun onColorSelected(color: Int, colorHex: String) {
//                        mMaterialColorCircle = colorHex
                        setButtonBackground(binding.materialDialogPickerCircleBtn, color)
                        colorIdSelected = CATEGORY_COLOR_MAP.get(colorHex)!!
                        binding.bigCategorySubmitBtn.isEnabled = (categoryName != "" && colorIdSelected != 0)

                        Log.d("TAG", "onColorSelected: $color, $colorHex, ${CATEGORY_COLOR_MAP.get(colorHex)}]")
                    }
                })
                .setNegativeButton("취소")
                .setPositiveButton("확인")
                .show()
        }

        // 등록 완료 버튼 클릭 시, POST 요청
        binding.bigCategorySubmitBtn.setOnClickListener {
            if(binding.bigCategoryNameTx.text.isNullOrBlank()) {
                Toast.makeText(addCategoryActivity, "카테고리명을 작성해주세요", Toast.LENGTH_SHORT).show()
            } else if(colorIdSelected == 0) {
                Toast.makeText(addCategoryActivity, "대표 색상을 선택해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "onCreateView: ${binding.bigCategoryNameEt.text} + $subCategoryNameList + $colorIdSelected")

                var userId = MainApplication.prefs.getString("userId", "").toInt()

                val retrofit = ApiClient.retrofit()
                val service = retrofit.create(PostCategoryService::class.java)

                service.addBigCategory(
                    PostCategoryReq(
                        colorIdSelected,
                        subCategoryNameList,
                        categoryName
                    ), userId).enqueue(MethodCallback.generalCallback<BaseRes, BaseRes, BaseRes> { })
            }
        }

        return binding.root
    }

    private fun setButtonBackground(btn: AppCompatButton, color: Int) {
        btn.setBackgroundResource(R.drawable.color_picked_circle)
        btn.setBackgroundColor(color)

    }

    private fun FlexboxLayout.addChip(text: String) {
        val chip = LayoutInflater.from(context).inflate(R.layout.item_category_chip, null) as Chip
        chip.text = text

        val layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT)
        layoutParams.rightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4F, resources.displayMetrics).roundToInt()

        chip.setOnCloseIconClickListener {
            removeView(chip as View)
        }

        addView(chip, childCount - 1, layoutParams)
    }

    private fun FlexboxLayout.getAllChips(): List<Chip> {
        return (0 until childCount).mapNotNull { index ->
            getChildAt(index) as? Chip
        }
    }

    private fun FlexboxLayout.clearChips() {
        val chipViews = (0 until childCount).mapNotNull { index ->
            val view = getChildAt(index)
            if (view is Chip) view else null
        }
        chipViews.forEach { removeView(it) }
    }
}
