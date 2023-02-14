package com.example.lifolio.ViewSmallCategory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.R
import com.example.lifolio.ViewAllMyLifolio.ViewWay1Fragment
import com.example.lifolio.ViewAllMyLifolio.ViewWay2Fragment
import com.example.lifolio.ViewAllMyLifolio.ViewWay3Fragment
import com.example.lifolio.databinding.ActivityViewSmallCategoryBinding
import kotlinx.android.synthetic.main.activity_view_all_lifolio.view.*
import kotlinx.android.synthetic.main.activity_view_small_category.view.*

class ViewSmallCategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewSmallCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSmallCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewSmallCategoryViewWayGroup.view_small_category_view_way1_btn.isChecked = true // 디폴트값으로 첫번째 보여주기 방식 체크해주기
        supportFragmentManager
            .beginTransaction()
            .replace(binding.viewSmallCategoryFragmentViewConst.id, ViewSmallCategoryWay1Fragment())
            .commitAllowingStateLoss()

        binding.viewSmallCategoryViewWayGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.view_small_category_view_way1_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewSmallCategoryFragmentViewConst.id, ViewSmallCategoryWay1Fragment())
                        .commitAllowingStateLoss()
                }
                R.id.view_small_category_view_way2_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewSmallCategoryFragmentViewConst.id, ViewSmallCategoryWay2Fragment())
                        .commitAllowingStateLoss()
                }
                R.id.view_small_category_view_way3_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewSmallCategoryFragmentViewConst.id, ViewSmallCategoryWay3Fragment())
                        .commitAllowingStateLoss()
                }
            }
        }
    }
}