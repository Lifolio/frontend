package com.example.lifolio.SignUp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.ApiService
import com.example.lifolio.SignUp.models.Request
import com.example.lifolio.databinding.ActivityCreateidBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateIdActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateidBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        val name = extras!!["name"] as String
        val phoneNumber = extras!!["phoneNumber"] as String
        val getName = binding.createidGetnameTv
        getName.text = name// 전 단계에서 입력한 이름 전달받기

        binding.createidBackBtn.setOnClickListener { //뒤로가기 버튼
            onBackPressed()
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }

        val retrofit = Retrofit.Builder()// Retrofit2 사용을 위한 선언
            .baseUrl("https://www.lifolio.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.createidErrorNicknameTv.setVisibility(View.GONE) //디폴트로 닉네임 중복 에러 메시지 숨기기
        binding.createidErrorIdTv.setVisibility(View.GONE) // 디폴트로 아이디 중복 에러 메시지 숨기기
        binding.createidErrorTv.setVisibility(View.GONE) // 디폴트로 비밀번호 에러 메시지 숨기기
        val apiService = retrofit.create(ApiService::class.java) // Retrofit2 interface 연결

        lateinit var userNickname : String // 유저 닉네임을 저장하는 변수
        lateinit var userId : String // 유저 아이디를 저장하는 변수
        lateinit var userPassword : String // 유저 비밀번호를 저장하는 변수
        lateinit var checkPassword : String // 비밀번호 재입력한 값을 저장하는 변수


        // 닉네임 입력 받는 editText 에서 입력할때마다 이벤트 발생
        val getNickname = binding.createidGetnicknameEt
        getNickname.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.createidErrorNicknameTv.setVisibility(View.GONE)
                userNickname = getNickname.text.toString()
                apiService.getCheckUserNickname(userNickname).enqueue(object : Callback<Response>{
                    override fun onResponse(
                        call: Call<Response>,
                        response: retrofit2.Response<Response>){
                        if (response.isSuccessful){
                            val responseData = response.body()
                            if (responseData != null){
                                when(responseData.code){
                                    1000 -> binding.createidErrorNicknameTv.setVisibility(View.GONE) // 중복되지 않을때 메시지를 숨김
                                    2021 -> binding.createidErrorNicknameTv.setVisibility(View.VISIBLE) // 중복될때 메시지를 보여줌
                                }
                            }
                        }
                        else{
                        }
                    }
                    override fun onFailure(call: Call<Response>, t: Throwable) {
                    }
                })
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        // 아이디 입력 받는 editText 에서 입력할때마다 이벤트 발생
        val getId = binding.createidGetidEt
        getId.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.createidErrorIdTv.setVisibility(View.GONE)
                userId = getId.text.toString()
                apiService.getCheckUserId(userId).enqueue(object : Callback<Response>{ // 서버에 입력받은 아이디 중복체크하는 코드
                    override fun onResponse(
                        call: Call<Response>,
                        response: retrofit2.Response<Response>){
                        if (response.isSuccessful){ // 통신 성공했을때?
                            val responseData = response.body()
                            if (responseData != null){
                                when(responseData.code){ //예외처리
                                    1000 -> binding.createidErrorIdTv.setVisibility(View.GONE) // 중복되지 않을때 메시지를 숨김
                                    2020 -> binding.createidErrorIdTv.setVisibility(View.VISIBLE) // 중복될때 메시지를 보여줌
                                }
                            }
                        }
                        else{
                        }
                    }
                    override fun onFailure(call: Call<Response>, t: Throwable) { // 통신 실패했을때, 에러났을때? 실행되는 코드
                    }
                })
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        // 비밀번호 입력 받는 editText 에서 입력할때마다 이벤트 발생
        val getPassword = binding.createidGetpasswordEt
        getPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userPassword = getPassword.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        // 비밀번호 확인시 입력받는 editText 에서 입력할때마다 이벤트 발생
        val getCheckPassword = binding.createidConfirmpasswordEt
        getCheckPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkPassword = getCheckPassword.text.toString()
                if (userPassword != checkPassword){
                    binding.createidErrorTv.setVisibility(View.VISIBLE)
                }
                else{
                    binding.createidErrorTv.setVisibility(View.GONE)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.createidEndSignupBtn.setOnClickListener{
            apiService.createNewUser(
                Request(
                    binding.createidGetidEt.text.toString(),
                    binding.createidGetpasswordEt.text.toString(),
                    name,
                    binding.createidGetnicknameEt.text.toString(),
                    phoneNumber
                )).enqueue(object : Callback<Response>{ // 서버에 입력받은 아이디 중복체크하는 코드
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>){
                    if (response.isSuccessful){ // 통신 성공했을때?
                        val responseData = response.body()
                        if (responseData != null){
                            when(responseData.code){ //예외처리
                                1000 -> Toast.makeText(this@CreateIdActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                                2001 -> Toast.makeText(this@CreateIdActivity, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                                2002 -> Toast.makeText(this@CreateIdActivity, "이메일은 30자리 미만으로 입력해주세요.", Toast.LENGTH_SHORT).show()
                                2003 -> Toast.makeText(this@CreateIdActivity, "이메일의 형식을 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show()
                                2004 -> Toast.makeText(this@CreateIdActivity, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                                2005 -> Toast.makeText(this@CreateIdActivity, "비밀번호는 6~20자리입니다..", Toast.LENGTH_SHORT).show()
                                2007 -> Toast.makeText(this@CreateIdActivity, "닉네임은 최대 20자리입니다.", Toast.LENGTH_SHORT).show()
                                3001 -> Toast.makeText(this@CreateIdActivity, "중복된 이메일입니다.", Toast.LENGTH_SHORT).show()
                                2017 -> Toast.makeText(this@CreateIdActivity, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                                2021 -> Toast.makeText(this@CreateIdActivity, "아이디는 최대 20자리입니다.", Toast.LENGTH_SHORT).show()
                                2022 -> Toast.makeText(this@CreateIdActivity, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                                4000 -> Toast.makeText(this@CreateIdActivity, "데이터베이스 에러.", Toast.LENGTH_SHORT).show()
                                else -> Toast.makeText(this@CreateIdActivity, "알 수 없는 에러", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                    }
                }
                override fun onFailure(call: Call<Response>, t: Throwable) { // 통신 실패했을때, 에러났을때? 실행되는 코드
                }
            })
        }
    }
}