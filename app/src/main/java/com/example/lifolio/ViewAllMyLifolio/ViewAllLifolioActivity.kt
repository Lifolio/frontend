package com.example.lifolio.ViewAllMyLifolio

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.lifolio.EditCategory.EditCategoryActivity
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityViewAllLifolioBinding
import kotlinx.android.synthetic.main.activity_view_all_lifolio.view.*


class ViewAllLifolioActivity : AppCompatActivity(){
    private lateinit var binding: ActivityViewAllLifolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllLifolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewAllLifolioToolbar.ibToolbar.setOnClickListener { // 툴바에 버튼을 눌렀을때
            toggleDrawerLayout(binding.root)
        }

        binding.viewAllLifolioNavi.naviViewAllLifolioHeaderEditBtn.setOnClickListener {
            startActivity(Intent(this,EditCategoryActivity::class.java))
        }

        binding.viewAllLifolioViewWayGroup.view_all_lifolio_view_way1_btn.isChecked = true // 디폴트값으로 첫번째 보여주기 방식 체크해주기
        supportFragmentManager
            .beginTransaction()
            .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay1Fragment())
            .commitAllowingStateLoss()


        binding.viewAllLifolioViewWayGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.view_all_lifolio_view_way1_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay1Fragment())
                        .commitAllowingStateLoss()
                }
                R.id.view_all_lifolio_view_way2_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay2Fragment())
                        .commitAllowingStateLoss()
                }
                R.id.view_all_lifolio_view_way3_btn ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.viewAllLifolioFragmentViewConst.id, ViewWay3Fragment())
                        .commitAllowingStateLoss()
                }
            }
        }

    }

    private fun toggleDrawerLayout(drawerLayout: DrawerLayout) { // 툴바에 버튼을 눌렀을때 실행할 함수

        if(!drawerLayout.isDrawerOpen(GravityCompat.START)) { // navigation drawer 띄우기
            drawerLayout.openDrawer(GravityCompat.START)
        }
        else {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() { //뒤로가기 했을 때
        if (binding.root.isDrawerOpen(GravityCompat.START)) { // navigation drawer가 띄워진 상태에서 뒤로가기를 눌렀을때
            binding.root.closeDrawer(GravityCompat.START)
        } else { // naigation drawer가 띄워지지 않은 상태에서 뒤로가기를 눌렀을때
            super.onBackPressed()
        }
    }
}