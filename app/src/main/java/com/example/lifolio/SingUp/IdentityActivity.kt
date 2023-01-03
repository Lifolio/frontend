package com.example.lifolio.SingUp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityIdentityBinding

class IdentityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIdentityBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkBox = binding.identityAgreeCheckbox
        checkBox.setOnClickListener {
            if (checkBox.isChecked()){ // 체크돼있으면 레이아웃 숨기기
                binding.identityTermsConst.setVisibility(View.GONE)
            }
            else{ // 체크 안돼있으면 레이아웃 보여주기
                binding.identityTermsConst.setVisibility(View.VISIBLE)
            }
        }

        val selectTelecom = binding.identitySelectTelecomBtn
        val telecom = arrayOf("SKT","KT","LG")
        selectTelecom.setOnClickListener { // 통신사 선택 다이얼로그
            AlertDialog.Builder(this)
                .setTitle("통신사")
                .setItems(telecom, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        val currentItem = telecom[which]
                        binding.identityTelecomTv.text = currentItem
                    }
                })
                .show()
        }

        binding.identityRequestNumConst.setVisibility(View.GONE) // 디폴트로 인증번호 입력창 숨기기
        val request = binding.identityAgreeRequestBtn
        request.setOnClickListener {
            binding.identityAgreeRequestBtn.text="다시요청"
            binding.identityRequestNumConst.setVisibility(View.VISIBLE)
        }

        binding.identityNextBtn.setOnClickListener {
            val intent = Intent(this,CreateIdActivity::class.java)
            startActivity(intent)
        }
    }
}