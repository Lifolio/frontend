package com.example.lifolio.Category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityAddCategoryBinding
import com.example.lifolio.databinding.ActivityEditBigCategoryBinding
import com.example.lifolio.databinding.FragmentBigCategoryBinding

class EditBigCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBigCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBigCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBigCategorySelectsizeSwch.isEnabled = false
    }
}