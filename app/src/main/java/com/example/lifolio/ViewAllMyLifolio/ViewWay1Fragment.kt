package com.example.lifolio.ViewAllMyLifolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentViewWay1Binding

class ViewWay1Fragment : Fragment()  {
    private lateinit var binding : FragmentViewWay1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewWay1Binding.inflate(layoutInflater)
        return binding.root
    }
}