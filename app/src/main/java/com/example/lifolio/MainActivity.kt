package com.example.lifolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lifolio.SingUp.TermsOfServiceActivity
import com.example.lifolio.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, TermsOfServiceActivity::class.java)
            startActivity(intent)
        }
    }
}