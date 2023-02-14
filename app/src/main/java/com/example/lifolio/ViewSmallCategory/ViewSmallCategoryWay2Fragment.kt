package com.example.lifolio.ViewSmallCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentViewSmallCategoryWay2Binding

class ViewSmallCategoryWay2Fragment : Fragment() {
    private lateinit var binding : FragmentViewSmallCategoryWay2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewSmallCategoryWay2Binding.inflate(layoutInflater)
        return binding.root
    }
}