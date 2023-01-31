package com.example.lifolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifolio.databinding.FragmentSetNotificationBinding


class SetNotificationFragment : Fragment() {

    private lateinit var binding: FragmentSetNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_notification, container, false)
    }
}