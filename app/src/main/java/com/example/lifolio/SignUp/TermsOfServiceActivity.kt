package com.example.lifolio.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityTermsofserviceBinding

class TermsOfServiceActivity : AppCompatActivity() { //여러 약관들을 동의 받는 Activity
    private lateinit var binding : ActivityTermsofserviceBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsofserviceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.termsofserviceBackBtn.setOnClickListener { // 뒤로가기 버튼
            onBackPressed()
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }

        binding.termsofserviceNextBtn.isEnabled = false // 디폴트로 회원가입 다음 단계로 가는 버튼 비활성화
        val FirstTosChechBox = binding.termsofserviceTos1Checkbox
        val SecondTosCheckBox = binding.termsofserviceTos2Checkbox

        binding.termsofserviceTos1Checkbox.setOnClickListener{
            binding.termsofserviceNextBtn.isEnabled = FirstTosChechBox.isChecked && SecondTosCheckBox.isChecked
        }

        binding.termsofserviceTos2Checkbox.setOnClickListener {
            binding.termsofserviceNextBtn.isEnabled = FirstTosChechBox.isChecked && SecondTosCheckBox.isChecked
        }

        binding.termsofserviceTos1Btn.setOnClickListener { // 이용 약관 내용
            val intent = Intent(this,TosActivity::class.java)
            val tos : String
            intent.putExtra("tos","1")
            startActivity(intent)
        }

        binding.termsofserviceTos2Btn.setOnClickListener { // 개인 정보 처리 방침 내용
            val intent = Intent(this,TosActivity::class.java)
            val tos : String
            intent.putExtra("tos","2")
            startActivity(intent)
        }

        binding.termsofserviceTos3Btn.setOnClickListener { // 마케팅 정보 수집 및 수신동의
            val intent = Intent(this,TosActivity::class.java)
            val tos : String
            intent.putExtra("tos","3")
            startActivity(intent)
        }

        binding.termsofserviceNextBtn.setOnClickListener { // 회원가입 다음 단계로 가는 버튼
            val intent = Intent(this,IdentityActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }
    }
}