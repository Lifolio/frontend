package com.example.lifolio.SignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.FragmentIdentityTos2Binding

class IdentityTos2Fragment : Fragment() {
    private lateinit var binding : FragmentIdentityTos2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIdentityTos2Binding.inflate(layoutInflater)
        return binding.root
    }
}