package com.example.lifolio.SingUp

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

        binding.termsofserviceTos1Btn.setOnClickListener {
            val intent = Intent(this,TosActivity::class.java)
            val tos : String
            intent.putExtra("tos","1")
            startActivity(intent)
        }

        binding.termsofserviceTos2Btn.setOnClickListener {
            val intent = Intent(this,TosActivity::class.java)
            val tos : String
            intent.putExtra("tos","2")
            startActivity(intent)
        }

        binding.termsofserviceTos3Btn.setOnClickListener {
            val intent = Intent(this,TosActivity::class.java)
            val tos : String
            intent.putExtra("tos","3")
            startActivity(intent)
        }

        binding.termsofserviceNextBtn.setOnClickListener {
            val intent = Intent(this,IdentityActivity::class.java)
            startActivity(intent)
        }
    }
}