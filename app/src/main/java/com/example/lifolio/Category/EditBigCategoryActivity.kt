package com.example.lifolio.Category

import androidx.appcompat.app.AppCompatActivity
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
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityAddCategoryBinding
import com.example.lifolio.databinding.ActivityEditBigCategoryBinding
import com.example.lifolio.databinding.FragmentBigCategoryBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.listener.ColorListener
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.github.dhaval2404.colorpicker.util.ColorUtil
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.chip.Chip
import kotlin.math.roundToInt

class EditBigCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBigCategoryBinding

    var subCategoryNameList: ArrayList<String> = arrayListOf()
    var colorIdSelected: Int = 0
    var categoryName: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBigCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBigCategorySelectsizeSwch.isEnabled = false

        // 카테고리명 칸, 대표색상 채워지면 버튼 활성화
        binding.editBigCategoryNameEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                categoryName = binding.editBigCategoryNameEt.text.toString()
                binding.editBigCategorySubmitBtn.isEnabled = (categoryName != "" && colorIdSelected != 0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        // EditText 값 입력 시 Chip 생성
        binding.editBigCategoryChipEt.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val et = v as EditText
                val name = et.text.toString()
                subCategoryNameList.add(name)
                binding.editBigCategoryFlexboxlt.addChip(name)

                et.text = null
            }
            return@setOnKeyListener false
        }

        // ColorPicker Dialog 띄우기
        binding.materialDialogPickerCircleBtn.setOnClickListener() {
            MaterialColorPickerDialog
                .Builder(this)
                .setTitle("카테고리 색상 선택")
                .setColorShape(ColorShape.CIRCLE)
                .setColorSwatch(ColorSwatch._300)
                .setDefaultColor(ColorUtil.parseColor("#ffffff"))
                .setColors(BigCategoryFragment.CATEGORY_COLOR_LIST)
                .setTickColorPerCard(true)
                .setColorListener (object : ColorListener {   // Dialog 확인을 누르면, Color ID 혹은 Hex String 값 반환. colorHex 쓰면 됨
                    override fun onColorSelected(color: Int, colorHex: String) {
//                        mMaterialColorCircle = colorHex
                        var colorId = BigCategoryFragment.CATEGORY_COLOR_MAP.get(colorHex)
                        var colorResId = colorId?.let { it1 -> BigCategoryFragment.CATEGORY_COLOR_RES_ID_LIST.get(it1 - 1) }
                        if (colorId != null) {
                            if (colorResId != null) {
                                setButtonBackground(binding.materialDialogPickerCircleBtn, colorResId)
                            }
                        }
                        colorIdSelected = BigCategoryFragment.CATEGORY_COLOR_MAP.get(colorHex)!!
                        binding.editBigCategorySubmitBtn.isEnabled = (categoryName != "" && colorIdSelected != 0)

                        Log.d("TAG", "onColorSelected: $color, $colorHex, $colorId, $colorResId]")
                    }
                })
                .setNegativeButton("취소")
                .setPositiveButton("확인")
                .show()
        }



    }
    private fun setButtonBackground(btn: AppCompatButton, colorId: Int) {
        btn.background = ResourcesCompat.getDrawable(resources, R.drawable.color_picked_circle, null)
        btn.backgroundTintList = ContextCompat.getColorStateList(this, colorId)
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