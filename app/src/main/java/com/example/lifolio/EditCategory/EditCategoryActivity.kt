package com.example.lifolio.EditCategory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifolio.EditCategory.models.BigCategory
import com.example.lifolio.EditCategory.models.SmallCategory
import com.example.lifolio.databinding.ActivityEditCategoryBinding

class EditCategoryActivity : AppCompatActivity(){
    private lateinit var binding : ActivityEditCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bigCategoryList : MutableList<BigCategory> = mutableListOf()
        val bigCategoryRVAd = BigCategoryRVAd(this,bigCategoryList)

        binding.editcategoryRv.adapter = bigCategoryRVAd
        binding.editcategoryRv.layoutManager = LinearLayoutManager(this)


        val itemTouchHelperCallback = ItemTouchHelperCallback(bigCategoryRVAd)
        val helper = ItemTouchHelper(itemTouchHelperCallback)
        helper.attachToRecyclerView(binding.editcategoryRv)


        bigCategoryList.add(BigCategory("인생", mutableListOf(SmallCategory("추억"),
            SmallCategory("여행"),
            SmallCategory("친구"))))
        bigCategoryList.add(BigCategory("학교", mutableListOf(SmallCategory("학회"),
            SmallCategory("UMC"))))
        bigCategoryList.add(BigCategory("업무", mutableListOf(SmallCategory("프로젝트"),
            SmallCategory("Lifolio"))))

    }
}