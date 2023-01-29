package com.example.lifolio.ViewAllMyLifolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentViewWay2Binding

class ViewWay2Fragment : Fragment()  {
    private lateinit var binding : FragmentViewWay2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewWay2Binding.inflate(layoutInflater)
        return binding.root
    }
}