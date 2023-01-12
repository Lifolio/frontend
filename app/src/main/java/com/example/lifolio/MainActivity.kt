package com.example.lifolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifolio.CustomOfTheYear.CustomOfTheYearActivity
import com.example.lifolio.Login.LoginActivity
import com.example.lifolio.SignUp.TermsOfServiceActivity
import com.example.lifolio.databinding.ActivityMainBinding

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
            val intent = Intent(this, CustomOfTheYearActivity::class.java)
            startActivity(intent)
        }

        // 챠코 테스트 할 때만 임시로 쓰겠습니다
        /*
        binding.btnId.setOnClickListener {
            val intent = Intent(this, IDFoundActivity::class.java)
            startActivity(intent)
        }
        */
    }
}