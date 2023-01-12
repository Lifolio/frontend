package com.example.lifolio.CustomOfTheYear

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityCustomoftheyearBinding

class CustomOfTheYearActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCustomoftheyearBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomoftheyearBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}