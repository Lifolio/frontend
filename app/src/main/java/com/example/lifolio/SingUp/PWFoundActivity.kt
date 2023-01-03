package com.example.lifolio.SingUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityPwfoundBinding

class PWFoundActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPwfoundBinding // binding 변수 선언
    override fun OnCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPwfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idfoundGotologinBtn.setOnClickListener {

        }
    }
}
