package com.example.lifolio.Category

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.lifolio.PortfolioFragment
import com.example.lifolio.R
import com.example.lifolio.databinding.FragmentSmallCategoryBinding

class SmallCategoryFragment : Fragment() {
    private lateinit var binding : FragmentSmallCategoryBinding
    private lateinit var addCategoryActivity: AddCategoryActivity
    private lateinit var supCategorySpinner: Spinner
    var categoryName: String = ""

    companion object {
        const val TAG : String = "SMALL_CATEGORY_FRAGMENT"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        addCategoryActivity = context as AddCategoryActivity // Context를 액티비티로 형변환해서 할당
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmallCategoryBinding.inflate(layoutInflater)


        supCategorySpinner = binding.smallCategorySupCategorySp
        var supCateogyDummyList: ArrayList<String> = arrayListOf()
        supCateogyDummyList.add("상위 카테고리 선택")
        supCateogyDummyList.add("일상")
        supCateogyDummyList.add("취미")
        supCateogyDummyList.add("동아리")
        supCateogyDummyList.add("여행")
        supCateogyDummyList.add("기타")

        supCategorySpinner.adapter = ArrayAdapter(addCategoryActivity, R.layout.item_record_spinner, supCateogyDummyList)

        // 카테고리명 칸 채워지면 버튼 활성화
        binding.smallCategoryNameEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                categoryName = binding.smallCategoryNameEt.text.toString()
                binding.smallCategorySubmitBtn.isEnabled = (categoryName != "") // 추후 수정 필요 - spinner 선택 경우 포함
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        return binding.root
    }


}