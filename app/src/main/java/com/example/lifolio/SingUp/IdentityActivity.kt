package com.example.lifolio.SingUp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.databinding.ActivityIdentityBinding
import kotlinx.coroutines.internal.synchronized

class IdentityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIdentityBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.identityBackBtn.setOnClickListener { // 뒤로가기 버튼
            onBackPressed()
            overridePendingTransition(0,0)
        }

        val handler = Handler(Looper.getMainLooper()
        )

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
        var status = false
        var A = false
        request.setOnClickListener { // 인증시간 타이머(feat.데런) 약 10분소요
            status = !status
            var totalTime : Int = 300 // 인증번호 전체 시간 저장
            binding.identityAgreeRequestBtn.text="다시요청"
            binding.identityRequestNumConst.setVisibility(View.VISIBLE)
            if(!status) {
                A = !A
                Thread(){
                    while (!A && totalTime>0){
                        Thread.sleep(1000)
                        totalTime--
                        handler.post{
                            binding.identityMinTv.text=String.format("%02d",totalTime / 60)// 인증 번호 남은 분
                            binding.identitySecTv.text=String.format("%02d",totalTime % 60)// 인증 번호 남은 초
                        }
                    }
                    totalTime = 300
                }.start()
            }else {
                A = !A
                Thread(){
                    while (A && totalTime>0){
                        Thread.sleep(1000)
                        totalTime--
                        handler.post{
                            binding.identityMinTv.text=String.format("%02d",totalTime / 60)// 인증 번호 남은 분
                            binding.identitySecTv.text=String.format("%02d",totalTime % 60)// 인증 번호 남은 초
                        }
                    }
                    totalTime = 300
                }.start()
            }
        }

        binding.identityNextBtn.setOnClickListener {
            val intent = Intent(this,CreateIdActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
        }
    }

}