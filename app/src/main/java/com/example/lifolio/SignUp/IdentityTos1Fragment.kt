package com.example.lifolio.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentIdentityTos1Binding

class IdentityTos1Fragment : Fragment() {
    private lateinit var binding : FragmentIdentityTos1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIdentityTos1Binding.inflate(layoutInflater)
        return binding.root
    }
}