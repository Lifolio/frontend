package com.example.lifolio.ViewAllMyLifolio

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.lifolio.Category.BigCategoryFragment.Companion.TAG
import com.example.lifolio.databinding.ActivityViewAllLifolioBinding
import com.google.android.material.navigation.NavigationView


class ViewAllLifolioActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private lateinit var binding: ActivityViewAllLifolioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllLifolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewAllLifolioToolbar.ibToolbar.setOnClickListener {
            toggleDrawerLayout(binding.root)
        }
        binding.navView.setNavigationItemSelectedListener(this)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onNavigationItemSelected: ${item.title}")
        return true
    }

    private fun toggleDrawerLayout(drawerLayout: DrawerLayout) {

        if(!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        else {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() { //뒤로가기 했을 때
        val drawerLayout = binding.root
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}