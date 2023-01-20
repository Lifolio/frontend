package com.example.lifolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.lifolio.Home.ApiService
import com.example.lifolio.Home.ResponseHome
import com.example.lifolio.databinding.ActivityMainBinding
import com.example.lifolio.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        edit1_btn.visibility = View.INVISIBLE

//        val retrofit = Retrofit.Builder()// Retrofit2 사용을 위한 선언
//            .baseUrl("https://www.lifolio.shop/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // Retrofit2 interface 연결
//        val apiService = retrofit.create(ApiService::class.java)

        //토큰 문제 해결 후 작성
//        apiService.homeView().enqueue(object : Callback<ResponseHome>{
//            override fun onResponse(call: Call<ResponseHome>, response: Response<ResponseHome>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ResponseHome>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })

        //item 1 클릭 이벤트
        binding.open1Btn.setOnClickListener{
            if(binding.itemFirstExpand.visibility == View.VISIBLE) {
                binding.itemFirstExpand.visibility = View.GONE
                binding.open1Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemFirstExpand.visibility = View.VISIBLE
                binding.open1Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        //item 2 클릭 이벤트
        binding.open2Btn.setOnClickListener{
            if(binding.itemSecondExpand.visibility == View.VISIBLE) {
                binding.itemSecondExpand.visibility = View.GONE
                binding.open2Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemSecondExpand.visibility = View.VISIBLE
                binding.open2Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        //item 3 클릭 이벤트
        binding.open3Btn.setOnClickListener{
            if(binding.itemThirdExpand.visibility == View.VISIBLE) {
                binding.itemThirdExpand.visibility = View.GONE
                binding.open3Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemThirdExpand.visibility = View.VISIBLE
                binding.open3Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        //item 4 클릭 이벤트
        binding.open4Btn.setOnClickListener{
            if(binding.itemFourthExpand.visibility == View.VISIBLE) {
                binding.itemFourthExpand.visibility = View.GONE
                binding.open4Btn.animate().apply {
                    duration = 300
                    rotation(0f)
                }
            } else {
                binding.itemFourthExpand.visibility = View.VISIBLE
                binding.open4Btn.animate().apply {
                    duration = 300
                    rotation(180f)
                }
            }
        }

        binding.editLifolioBtn.setOnClickListener{

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setContentView(root: LinearLayout) {

    }
}
