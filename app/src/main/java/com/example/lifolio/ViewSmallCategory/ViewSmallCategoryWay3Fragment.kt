package com.example.lifolio.ViewSmallCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentViewSmallCategoryWay3Binding

class ViewSmallCategoryWay3Fragment : Fragment() {
    private lateinit var binding : FragmentViewSmallCategoryWay3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewSmallCategoryWay3Binding.inflate(layoutInflater)
        return binding.root
    }
}