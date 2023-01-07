package com.example.lifolio.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentTosfirstBinding

class TosfirstFragment : Fragment() {
    private lateinit var binding : FragmentTosfirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTosfirstBinding.inflate(layoutInflater)
        return binding.root
    }
}