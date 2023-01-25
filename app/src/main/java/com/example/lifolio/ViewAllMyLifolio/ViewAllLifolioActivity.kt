package com.example.lifolio.ViewAllMyLifolio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.lifolio.Category.BigCategoryFragment.Companion.TAG
import com.example.lifolio.EditCategory.EditCategoryActivity
import com.example.lifolio.databinding.ActivityViewAllLifolioBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.header_view_all_lifolio_navigation_drawer.view.*


class ViewAllLifolioActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private lateinit var binding: ActivityViewAllLifolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllLifolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewAllLifolioToolbar.ibToolbar.setOnClickListener { // 툴바에 버튼을 눌렀을때
            toggleDrawerLayout(binding.root)
        }
        binding.navView.setNavigationItemSelectedListener(this)

        val header: View = binding.navView.getHeaderView(0) // navigationDrawer Header에 접근하기 위한 선언

        header.view_all_lifolio_header_edit_btn.setOnClickListener { // navigationDrawer Header에 수정 버튼을 눌렀을때
            startActivity(Intent(this,EditCategoryActivity::class.java))
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean { // navigationDrawer에 각각 카테고리를 눌렀을때
        Log.d(TAG, "onNavigationItemSelected: ${item.title}")
        return true
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
        val drawerLayout = binding.root
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) { // navigation drawer가 띄워진 상태에서 뒤로가기를 눌렀을때
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}