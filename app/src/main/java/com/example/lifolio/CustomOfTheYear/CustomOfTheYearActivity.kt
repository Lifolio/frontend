package com.example.lifolio.CustomOfTheYear

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifolio.CustomOfTheYear.models.DataRVAd
import com.example.lifolio.CustomOfTheYear.models.OfTheGoalRequest
import com.example.lifolio.databinding.ActivityCustomoftheyearBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CustomOfTheYearActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCustomoftheyearBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomoftheyearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()// Retrofit2 사용을 위한 선언
            .baseUrl("https://www.lifolio.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(com.example.lifolio.CustomOfTheYear.models.ApiService::class.java) // Retrofit2 interface 연결

        binding.customoftheyearEditGoalEt.setVisibility(View.GONE) // 디폴트로 editText 숨기기
        binding.customoftheyearSaveGoalBtn.setVisibility(View.GONE) // 디폴트로 저장하기 버튼 숨기기

        binding.customoftheyearEiitGoalBtn.setOnClickListener{ // 수정 버튼을 눌렀을때
            binding.customoftheyearEditGoalTv.setVisibility(View.GONE) // 올해의 나 보여주는 textView 숨기기
            binding.customoftheyearEditGoalEt.setVisibility(View.VISIBLE) // 올해의 나 입력하는 editText 보여주기
            binding.customoftheyearEiitGoalBtn.setVisibility(View.GONE) // 수정 버튼 숨기기
            binding.customoftheyearSaveGoalBtn.setVisibility(View.VISIBLE) // 저장 버튼 보여주기

//                apiService.addOfTheYearGoal().enqueue(object : Callback<com.example.lifolio.CustomOfTheYear.models.Response> { // 서버에서 전에 기록 가져오기
//                    override fun onResponse(
//                        call: Call<com.example.lifolio.CustomOfTheYear.models.Response>,
//                        response: retrofit2.Response<com.example.lifolio.CustomOfTheYear.models.Response>){
//                        if (response.isSuccessful){
//                            val responseData = response.body()
//                            if (responseData != null){
//                            }
//                        }
//                    }
//                    override fun onFailure(call: Call<com.example.lifolio.CustomOfTheYear.models.Response>, t: Throwable) {
//                    }
//                })
            }

        binding.customoftheyearSaveGoalBtn.setOnClickListener {
            binding.customoftheyearEditGoalTv.setVisibility(View.VISIBLE) // 올해의 나 보여주는 textView 보여주기
            binding.customoftheyearEditGoalEt.setVisibility(View.GONE) // 올해의 나 입력하는 editText 숨기기
            binding.customoftheyearSaveGoalBtn.setVisibility(View.GONE) // 저장 버튼 숨기기
            binding.customoftheyearEiitGoalBtn.setVisibility(View.VISIBLE) // 수정 버튼 보여주기
        }

        binding.customoftheyear22yearHistoryConst.setVisibility(View.GONE)// 디폴트로 22년 기록 숨기기
        var state22History = true// 2022년의 기록을 보여주는 상태를 나타내주기 위한 변수
        binding.customoftheyear22yearBtn.setOnClickListener { // 2022년의 나 버튼을 눌렀을때
            if(state22History){
                binding.customoftheyear22yearHistoryConst.setVisibility(View.VISIBLE)
            }
            else{
                binding.customoftheyear22yearHistoryConst.setVisibility(View.GONE)
            }
            state22History = !state22History
        }

        binding.customoftheyear23yearHistoryConst.setVisibility(View.GONE)// 디폴트로 23년 기록 숨기기
        var state23History = true// 2023년의 기록을 보여주는 상태를 나타내주기 위한 변수
        binding.customoftheyear23yearBtn.setOnClickListener { // 2023년의 나 버튼을 눌렀을때
            if(state22History){
                binding.customoftheyear23yearHistoryConst.setVisibility(View.VISIBLE)
            }
            else{
                binding.customoftheyear23yearHistoryConst.setVisibility(View.GONE)
            }
            state23History = !state23History
//            apiService.getOfTheYear(23).enqueue(object : Callback<com.example.lifolio.CustomOfTheYear.models.Response> { // 서버에서 전에 기록 가져오기
//                override fun onResponse(
//                    call: Call<com.example.lifolio.CustomOfTheYear.models.Response>,
//                    response: retrofit2.Response<com.example.lifolio.CustomOfTheYear.models.Response>){
//                    if (response.isSuccessful){
//                        val responseData = response.body()
//                        if (responseData != null){
//                            val datalist = ArrayList(responseData.result)
//                            val dataRVAd = DataRVAd(datalist)
//                            binding.customoftheyear23yearHistoryRv.adapter = dataRVAd
//                            binding.customoftheyear23yearHistoryRv.layoutManager = LinearLayoutManager(this@CustomOfTheYearActivity)
//                            dataRVAd.notifyDataSetChanged()
//                        }
//                    }
//                }
//                override fun onFailure(call: Call<com.example.lifolio.CustomOfTheYear.models.Response>, t: Throwable) {
//                }
//            })
        }
    }
}