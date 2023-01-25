package com.example.lifolio.ViewAllMyLifolio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.lifolio.EditCategory.EditCategoryActivity

import com.example.lifolio.databinding.ActivityViewAllLifolioBinding



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