package com.example.lifolio.SignUp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.lifolio.SignUp.models.RequestFoundId
import com.example.lifolio.databinding.FragmentIdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IdFragment : Fragment() {
    private lateinit var binding : FragmentIdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIdBinding.inflate(layoutInflater)

        val retrofit = Retrofit.Builder()// Retrofit2 사용을 위한 선언
            .baseUrl("https://www.lifolio.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java) // Retrofit2 interface 연결

        val handler = Handler(Looper.getMainLooper()) // Thread 를 사용 하기 위한 Handler 선언

        val selectTelecom = binding.idSelectTelecomBtn
        val telecom = arrayOf("SKT","KT","LG")
        selectTelecom.setOnClickListener { // 통신사 선택 다이얼로그
            AlertDialog.Builder(requireActivity())
                .setTitle("통신사")
                .setItems(telecom, object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        val currentItem = telecom[which]
                        binding.idTelecomTv.text = currentItem
                    }
                })
                .show()
        }

        var totalTime : Int = 300 // 인증번호 전체 시간 저장
        binding.idRequestAgreeNumConst.setVisibility(View.GONE) // 디폴트로 인증번호 입력창 숨기기
        val request = binding.idAgreeRequestBtn // 인증요청 버튼
        lateinit var serverRequestNumber : String // 서버에서 보낸 인증번호
        lateinit var contactNumber : String // 연락처 번호

        var status = false // Thread 로 타이머 돌릴때 필요한 변수
        var A = false // Thread 로 타이머 돌릴때 필요한 변수

        binding.idContactEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                contactNumber = binding.idContactEt.text.toString() // EditText의 연락처를 contactNumber 변수에 저장
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        request.setOnClickListener { // 인증 요청 버튼을 눌렀을때
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
            binding.idAgreeRequestBtn.text="다시요청" // 인증 요청 버튼 클릭시 text를 "다시 요청" 변경
            binding.idRequestAgreeNumConst.setVisibility(View.VISIBLE) // 인증번호 입력 창 보여주기
            if(!status) { // 이하 타이머 설정하는 부분
                A = !A
                Thread(){
                    while (!A && totalTime>0){ // 첫번째 Thread (번갈아가며 사용)
                        Thread.sleep(1000)
                        totalTime--
                        handler.post{
                            binding.idMinTv.text=String.format("%02d",totalTime / 60)// 인증 번호 남은 분
                            binding.idSecTv.text=String.format("%02d",totalTime % 60)// 인증 번호 남은 초
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
                            binding.idMinTv.text=String.format("%02d",totalTime / 60)// 인증 번호 남은 분
                            binding.idSecTv.text=String.format("%02d",totalTime % 60)// 인증 번호 남은 초
                        }
                    }
                    totalTime = 300
                }.start()
            }
        }

//        binding.idNextBtn.isEnabled = false // 디폴트로 다음단계 버튼 비활성화
        binding.idErrorRequestNumTv.setVisibility(View.GONE) // 디폴트로 인증번호 에러 메시지 숨기기
        val getRequestNumber = binding.idRequestAgreeNumEt // 인증번호 EditText
        var requestNumber : String? = null // 사용자가 입력한 인증번호

        // 인증번호를 입력하는 editText 에서 입력할때마다 이벤트 발생
        getRequestNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                requestNumber = getRequestNumber.text.toString()
                if (requestNumber == serverRequestNumber && totalTime >= 0){ // 작성한 인증번호가 같고 타이머가 유효하면
                    binding.idErrorRequestNumTv.setVisibility(View.GONE) // 에러 메시지 숨기기
                }
                else{ // 작성한 인증번호가 같지 않거나 타이머가 유효하지 않을 경우
                    binding.idErrorRequestNumTv.setVisibility(View.VISIBLE) // 에러 메시지 띄우기
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        var findUserId : String? = null

        binding.idNextBtn.setOnClickListener { // 다음 단계로 가는 버튼
            apiService.foundUserId(RequestFoundId(
                binding.idGetNameEt.text.toString(),
                contactNumber
            )).enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>){
                    if (response.isSuccessful){
                        val responseData = response.body()
                        if (responseData != null){
                            when(responseData.code){
                                1000 -> findUserId = responseData.result.username
                                2029 -> Toast.makeText(requireActivity(), "회원 정보에 일치하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
                                else -> Toast.makeText(requireActivity(), "알 수 없는 에러.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<Response>, t: Throwable) {
                }
            })
        }

        return binding.root
    }
}