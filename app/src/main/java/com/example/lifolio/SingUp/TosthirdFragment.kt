package com.example.lifolio.SingUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentTosthirdBinding

class TosthirdFragment: Fragment() {
    private lateinit var binding : FragmentTosthirdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTosthirdBinding.inflate(layoutInflater)
        return binding.root
    }
}