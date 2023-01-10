package com.example.lifolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lifolio.Home.HomeActivity
import com.example.lifolio.Login.LoginActivity
import com.example.lifolio.SignUp.TermsOfServiceActivity
import com.example.lifolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 자동 로그인 pref
        val pref = getSharedPreferences("username", 0)
        val savedUsername = pref.getString("username", "").toString()

        // 로그인 버튼
        binding.btnLogin.setOnClickListener {
            if (savedUsername.equals("")) { // 자동 로그인 아니라면
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else { // 자동 로그인 경우
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "로그인 하였습니다", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, TermsOfServiceActivity::class.java)
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