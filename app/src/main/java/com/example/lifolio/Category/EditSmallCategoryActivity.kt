package com.example.lifolio.Category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityEditBigCategoryBinding
import com.example.lifolio.databinding.ActivityEditSmallCategoryBinding

class EditSmallCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditSmallCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSmallCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBigCategorySelectsizeSwch.isEnabled = false
        binding.editBigCategorySelectsizeSwch.isChecked = true
    }
}