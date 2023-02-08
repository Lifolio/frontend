package com.example.lifolio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityBadgeNewBinding

class BadgeNewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBadgeNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBadgeNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}