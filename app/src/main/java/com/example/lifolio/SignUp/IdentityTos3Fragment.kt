package com.example.lifolio.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentIdentityTos3Binding

class IdentityTos3Fragment : Fragment() {
    private lateinit var binding : FragmentIdentityTos3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIdentityTos3Binding.inflate(layoutInflater)
        return binding.root
    }
}