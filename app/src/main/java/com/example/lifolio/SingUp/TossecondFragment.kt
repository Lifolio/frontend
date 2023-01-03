package com.example.lifolio.SingUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentTossecondBinding

class TossecondFragment : Fragment() {
    private lateinit var binding : FragmentTossecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTossecondBinding.inflate(layoutInflater)
        return binding.root
    }
}