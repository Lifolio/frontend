package com.example.lifolio.Category

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.lifolio.PortfolioFragment
import com.example.lifolio.R
import com.example.lifolio.databinding.FragmentBigCategoryBinding
import com.example.lifolio.databinding.ItemCategoryChipBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class BigCategoryFragment : Fragment() {
    private lateinit var binding : FragmentBigCategoryBinding
    private lateinit var addCategoryActivity: AddCategoryActivity
    private lateinit var chipGroup: ChipGroup

    companion object {
        const val TAG : String = "BIG_CATEGORY_FRAGMENT"

        fun newInstance() : PortfolioFragment {
            return PortfolioFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        addCategoryActivity = context as AddCategoryActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBigCategoryBinding.inflate(layoutInflater)

         // Dummy Chip 4개 출력
//        chipGroup = binding.bigCategoryChipgroupCg
//        for (i in 0..3) {
//            val chip = ItemCategoryChipBinding.inflate(layoutInflater).root as Chip
//
//            chip.text = "항목$i"
////            chip.chipStrokeColor = ContextCompat.getColorStateList(this, R.color.chip_selector_stroke_color)  // 안됨
////            chip.chipStrokeColor = myList
//
//            chip.setEnsureMinTouchTargetSize(false)
//            chip.chipBackgroundColor = ColorStateList.valueOf(
//                ContextCompat.getColor(
//                    addCategoryActivity,
//                    R.color.chip_selector_background_color
//                )
//            )
//            chip.chipStrokeColor = ColorStateList.valueOf(
//                ContextCompat.getColor(
//                    addCategoryActivity,
//                    R.color.chip_selector_primary_color
//                )
//            )
//            chip.setTextColor(
//                ColorStateList.valueOf(
//                    ContextCompat.getColor(
//                        addCategoryActivity,
//                        R.color.chip_selector_primary_color
//                    )
//                )
//            )
//            chip.chipStartPadding = 50F
//            chip.chipEndPadding = 40F
//
////            chip.isCheckable = true // 체크 표시 사용 여부
////            chip.closeIcon = getDrawable(R.drawable.ic_close) // chip close 아이콘 이미지 지정
////            chip.isCloseIconVisible = true // close icon 표시 여부
//
//            chip.chipStrokeWidth = 5.5f
//            chip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12f)
//
//
//
//            chipGroup!!.addView(chip) // chip group 에 해당 chip 추가
//        }
        return binding.root
    }
}