package com.example.lifolio.Category

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.lifolio.PortfolioFragment
import com.example.lifolio.R
import com.example.lifolio.databinding.FragmentBigCategoryBinding
import com.example.lifolio.databinding.ItemCategoryChipBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.listener.ColorListener
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.github.dhaval2404.colorpicker.util.ColorUtil.parseColor
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlin.math.roundToInt

class BigCategoryFragment : Fragment() {
    private lateinit var binding : FragmentBigCategoryBinding
    private lateinit var addCategoryActivity: AddCategoryActivity
    private lateinit var chipGroup : ChipGroup

    companion object {
        const val TAG : String = "BIG_CATEGORY_FRAGMENT"

        fun newInstance() : BigCategoryFragment {
            return BigCategoryFragment()
        }
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

        binding.bigCategoryChipEt.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val et = v as EditText
                val name = et.text.toString()

                binding.bigCategoryFlexboxlt.addChip(name)

                et.text = null
            }
            return@setOnKeyListener false
        }

        binding.materialDialogPickerCircleBtn.setOnClickListener() {
            MaterialColorPickerDialog
                .Builder(addCategoryActivity)
                .setTitle("카테고리 색상 선택")
                .setColorShape(ColorShape.CIRCLE)
                .setColorSwatch(ColorSwatch._300)
                .setDefaultColor(parseColor("#ffffff"))
                .setColors(resources.getStringArray(R.array.themeColorPickerHex))
                .setTickColorPerCard(true)
                .setColorListener (object : ColorListener {   // Dialog 확인을 누르면, Color ID 혹은 Hex String 값 반환. colorHex 쓰면 됨
                    override fun onColorSelected(color: Int, colorHex: String) {
//                        mMaterialColorCircle = colorHex
                        setButtonBackground(binding.materialDialogPickerCircleBtn, color)

                        Log.d("TAG", "onColorSelected: $color, $colorHex")
                    }
                })
                .setNegativeButton("취소")
                .setPositiveButton("확인")
                .show()
        }

        return binding.root
    }

    private fun setButtonBackground(btn: AppCompatButton, color: Int) {
        btn.setBackgroundResource(R.drawable.color_picked_circle)
        btn.setBackgroundColor(color)

    }

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