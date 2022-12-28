package com.example.lifolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifolio.databinding.ActivityHomeBinding
import com.example.lifolio.databinding.ActivityMainBinding
import com.kakao.sdk.user.UserApiClient

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카카오 소셜 로그인 받아온 것
        val nickname = binding.txNickname // 닉네임
        UserApiClient.instance.me { user, error ->
            nickname.text = "닉네임: ${user?.kakaoAccount?.profile?.nickname}"
        }

        val email = binding.txEmail // 이메일
        UserApiClient.instance.me { user, error ->
            email.text = "이메일: ${user?.kakaoAccount?.email}"
        }
    }
}