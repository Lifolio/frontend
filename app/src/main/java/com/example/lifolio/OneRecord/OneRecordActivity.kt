package com.example.lifolio.OneRecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifolio.databinding.ActivityOneRecordBinding

class OneRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOneRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 버튼
        binding.backBtn.setOnClickListener {
            onBackPressed()
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }
    }
}