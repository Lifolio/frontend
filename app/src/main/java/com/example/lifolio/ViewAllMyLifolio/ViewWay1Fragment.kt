package com.example.lifolio.ViewAllMyLifolio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.OneRecord.OneRecordActivity
import com.example.lifolio.databinding.FragmentViewWay1Binding

class ViewWay1Fragment : Fragment()  {
    private lateinit var binding : FragmentViewWay1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewWay1Binding.inflate(layoutInflater)

        binding.fragmentViewWay1Content1.setOnClickListener { // 도쿄여행 기록을 눌렀을때 한개기록보기로 이동하기
            val intent = Intent(context,OneRecordActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}