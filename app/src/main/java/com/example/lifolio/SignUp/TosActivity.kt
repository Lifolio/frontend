package com.example.lifolio.SignUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityTosBinding

class TosActivity : AppCompatActivity() { // 약관 각각의 내용을 보여주는 Activity
    private lateinit var binding: ActivityTosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        val tos = extras!!["tos"] as String

        if (tos == "1"){ //'이용 약관 동의(필수)'를 눌렀을때
            supportFragmentManager
                .beginTransaction()
                .replace(binding.tosConstraint.id,IdFragment())
                .commit()
        }
        else if (tos == "2"){//'개인 정보 처리 방침(필수)'를 눌렀을때
            supportFragmentManager
                .beginTransaction()
                .replace(binding.tosConstraint.id,TossecondFragment())
                .commit()
        }
        else if (tos == "3"){//'마케팅 정보 수집 및 수신 동의(선택)'를 눌렀을때
            supportFragmentManager
                .beginTransaction()
                .replace(binding.tosConstraint.id,TosthirdFragment())
                .commit()
        }
    }
}