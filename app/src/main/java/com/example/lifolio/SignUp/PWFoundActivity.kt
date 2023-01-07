package com.example.lifolio.SignUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityPwfoundBinding

class PWFoundActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPwfoundBinding // binding 변수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPwfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 새 비밀번호 & 비밀번호 확인 text 같으면 자동으로 editText 닫히고 버튼 색상 바뀌게 구현
    // editText 둘 다 완료되면 버튼 색상 바뀌고 Dialog 화면으로 넘어갈 수 있는 기능
    // Dialog 화면으로 넘어가면서 서버에 GET으로 새로운 pw 보내야 함
}
