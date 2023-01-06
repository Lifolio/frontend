package com.example.lifolio.SingUp

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

        lateinit var userName : String // 유저 이름을 저장하는 변수
        lateinit var userNickname : String // 유저 닉네임을 저장하는 변수
        lateinit var userId : String // 유저 아이디를 저장하는 변수
        lateinit var userPassword : String // 유저 비밀번호를 저장하는 변수
        lateinit var checkPassword : String // 비밀번호 재입력한 값을 저장하는 변수

        // 이름 입력 받는 editText 에서 엔터키를 누를때 이벤트
        val getName = binding.createidGetnameEt
        getName.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER){
                userName = getName.text.toString()
                true
            }
            else{
                false
            }
        }

        // 닉네임 입력 받는 editText 에서 엔터키를 누를때 이벤트
        val getNickname = binding.createidGetnicknameEt
        getNickname.setOnKeyListener{ v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER){
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
                true
            }
            else{
                false
            }
        }

        // 아이디 입력 받는 editText 에서 엔터키를 누를때 이벤트
        val getId = binding.createidGetidEt
        getId.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER){
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
                true
            }
            else{
                false
            }
        }

        // 비밀번호 입력 받는 editText 에서 엔터키를 누를때 이벤트
        val getPassword = binding.createidGetpasswordEt
        getPassword.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER){
                userPassword = getPassword.text.toString()
                true
            }
            else{
                false
            }
        }

        // 비밀번호 확인시 입력받는 editText 에서 엔터키를 누를때 이벤트
        val getCheckPassword = binding.createidConfirmpasswordEt
        getCheckPassword.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER){
                checkPassword = getCheckPassword.text.toString()
                if (userPassword != checkPassword){
                    binding.createidErrorTv.setVisibility(View.VISIBLE)
                }
                true
            }
            else{
                false
            }
        }
    }
}