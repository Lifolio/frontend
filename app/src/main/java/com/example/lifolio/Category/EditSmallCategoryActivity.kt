package com.example.lifolio.Category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityEditBigCategoryBinding
import com.example.lifolio.databinding.ActivityEditSmallCategoryBinding

class EditSmallCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditSmallCategoryBinding
    private lateinit var supCategorySpinner: Spinner
    var categoryName: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSmallCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editSmallCategorySelectsizeSwch.isEnabled = false
        binding.editSmallCategorySelectsizeSwch.isChecked = true


        supCategorySpinner = binding.editSmallCategorySpinnerSp
        var supCateogyDummyList: ArrayList<String> = arrayListOf()
        supCateogyDummyList.add("상위 카테고리 선택")
        supCateogyDummyList.add("일상")
        supCateogyDummyList.add("취미")
        supCateogyDummyList.add("동아리")
        supCateogyDummyList.add("여행")
        supCateogyDummyList.add("기타")

        supCategorySpinner.adapter = ArrayAdapter(this, R.layout.item_record_spinner, supCateogyDummyList)

        // 카테고리명 칸 채워지면 버튼 활성화
        binding.editSmallCategoryNameEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                categoryName = binding.editSmallCategoryNameEt.text.toString()
                binding.editSmallCategorySubmitBtn.isEnabled = (categoryName != "") // 추후 수정 필요 - spinner 선택 경우 포함
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }
}