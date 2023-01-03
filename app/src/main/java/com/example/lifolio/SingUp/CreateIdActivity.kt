package com.example.lifolio.SingUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityCreateidBinding

class CreateIdActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateidBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateidBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}