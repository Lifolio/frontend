package com.example.lifolio.SignUp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.ApiService
import com.example.lifolio.databinding.ActivityIdentityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IdentityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIdentityBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()// Retrofit2 사용을 위한 선언
            .baseUrl("https://www.lifolio.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java) // Retrofit2 interface 연결

        binding.identityBackBtn.setOnClickListener { // 뒤로가기 버튼
            onBackPressed()
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }

        val handler = Handler(Looper.getMainLooper()) // Thread 를 사용 하기 위한 Handler 선언

        val checkBox = binding.identityAgreeCheckbox
        checkBox.setOnClickListener { // 전체동의를 체크할때
            if (checkBox.isChecked()){ // 체크돼있으면 레이아웃 숨기기
                binding.identityTermsConst.setVisibility(View.GONE)
            }
            else{ // 체크 안돼있으면 레이아웃 보여주기
                binding.identityTermsConst.setVisibility(View.VISIBLE)
            }
        }

        val tos1Checkbox = binding.identityAgreeTos1Checkbox
        val tos2Checkbox = binding.identityAgreeTos2Checkbox
        val tos3Checkbox = binding.identityAgreeTos3Checkbox
        val tos4Checkbox = binding.identityAgreeTos4Checkbox

        tos1Checkbox.setOnClickListener{ // 전체 다 체크돼있으면 숨기기
            if (tos1Checkbox.isChecked && tos2Checkbox.isChecked && tos3Checkbox.isChecked && tos4Checkbox.isChecked){
                binding.identityTermsConst.setVisibility(View.GONE)
                checkBox.isChecked = true
            }
            else{
                binding.identityTermsConst.setVisibility(View.VISIBLE)
            }
        }

        tos2Checkbox.setOnClickListener{ // 전체 다 체크돼있으면 숨기기
            if (tos1Checkbox.isChecked && tos2Checkbox.isChecked && tos3Checkbox.isChecked && tos4Checkbox.isChecked){
                binding.identityTermsConst.setVisibility(View.GONE)
                checkBox.isChecked = true
            }
            else{
                binding.identityTermsConst.setVisibility(View.VISIBLE)
            }
        }

        tos3Checkbox.setOnClickListener{ // 전체 다 체크돼있으면 숨기기
            if (tos1Checkbox.isChecked && tos2Checkbox.isChecked && tos3Checkbox.isChecked && tos4Checkbox.isChecked){
                binding.identityTermsConst.setVisibility(View.GONE)
                checkBox.isChecked = true
            }
            else{
                binding.identityTermsConst.setVisibility(View.VISIBLE)
            }
        }

        tos4Checkbox.setOnClickListener{ // 전체 다 체크돼있으면 숨기기
            if (tos1Checkbox.isChecked && tos2Checkbox.isChecked && tos3Checkbox.isChecked && tos4Checkbox.isChecked){
                binding.identityTermsConst.setVisibility(View.GONE)
                checkBox.isChecked = true
            }
            else{
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

        var totalTime : Int = 300 // 인증번호 전체 시간 저장
        binding.identityRequestNumConst.setVisibility(View.GONE) // 디폴트로 인증번호 입력창 숨기기
        val request = binding.identityAgreeRequestBtn // 인증요청 버튼
        lateinit var serverRequestNumber : String // 서버에서 보낸 인증번호
        lateinit var contactNumber : String // 연락처 번호

        var status = false // Thread 로 타이머 돌릴때 필요한 변수
        var A = false // Thread 로 타이머 돌릴때 필요한 변수

        request.setOnClickListener { // 인증 요청 버튼을 눌렀을때
            contactNumber = binding.identityContactEt.text.toString() // EditText의 연락처를 contactNumber 변수에 저장
            apiService.getSMS(contactNumber).enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>){
                    if (response.isSuccessful){
                        val responseData = response.body()
                        if (responseData != null){
                            serverRequestNumber = responseData.result.number.toString()
                        }
                    }
                }
                override fun onFailure(call: Call<Response>, t: Throwable) {
                }
            })

            status = !status // Thread 를 두개 쓰기 위해 필요한 변수 // 인증시간 타이머(feat.데런) 약 10분소요해서 만들었다고 거짓말함
            totalTime  = 300
            binding.identityAgreeRequestBtn.text="다시요청" // 인증 요청 버튼 클릭시 text를 "다시 요청" 변경
            binding.identityRequestNumConst.setVisibility(View.VISIBLE) // 인증번호 입력 창 보여주기
            if(!status) { // 이하 타이머 설정하는 부분
                A = !A
                Thread(){
                    while (!A && totalTime>0){ // 첫번째 Thread (번갈아가며 사용)
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
                    while (A && totalTime>0){ // 두번째 Thread (번갈아가며 사용)
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

//        binding.identityNextBtn.isEnabled = false // 디폴트로 다음단계 버튼 비활성화
        binding.identityErrorRequestNumTv.setVisibility(View.GONE) // 디폴트로 인증번호 에러 메시지 숨기기
        // 인증번호를 입력 받는 editText 에서 엔터키를 누를때 이벤트
        val getRequestNumber = binding.identityRequestNumEt // 인증번호 EditText
        var requestNumber : String? = null // 사용자가 입력한 인증번호
        requestNumber = getRequestNumber.text.toString()
        getRequestNumber.setOnKeyListener { v, keyCode, event -> // 인증번호를 입력하는 EditText에서 Enter키를 누를때 이벤트
            if(event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER){
                requestNumber = getRequestNumber.text.toString()
                if (requestNumber == serverRequestNumber && totalTime >= 0 && binding.identityAgreeCheckbox.isChecked){ // 작성한 인증번호가 같고 타이머가 유효하면
                    binding.identityNextBtn.isEnabled = true
                    binding.identityErrorRequestNumTv.setVisibility(View.GONE) // 에러 메시지 숨기기
                }
                else{ // 작성한 인증번호가 같지 않거나 타이머가 유효하지 않을 경우
                    binding.identityNextBtn.isEnabled = false
                    binding.identityErrorRequestNumTv.setVisibility(View.VISIBLE) // 에러 메시지 띄우기
                }
                true
            }
            else{
                false
            }
        }

        binding.identityNextBtn.setOnClickListener { // 회원가입 다음 단계로 가는 버튼
            val intent = Intent(this,CreateIdActivity::class.java)
            val name : String = binding.identityNameEt.getText().toString()
            val phoneNumber : String = binding.identityRequestNumEt.getText().toString()
            intent.putExtra("name",name) // 이번 단계에서 입력한 이름 다음 단계로 넘겨주기
            intent.putExtra("phoneNumber",phoneNumber)
            startActivity(intent)
            overridePendingTransition(0,0)
        }
    }

}