package com.example.lifolio

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lifolio.GoalFragment.Companion.newInstance
import com.example.lifolio.My.MyFragment
import com.example.lifolio.My.MyFragment.Companion.newInstance
import com.example.lifolio.databinding.FragmentProfileBinding
import org.w3c.dom.Text

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var bnbActivity: BnbActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentProfileBinding.inflate(layoutInflater)

        binding.btnGotoBadges.setOnClickListener {
            val intent = Intent(requireContext(), BadgeNewActivity::class.java)
            startActivity(intent)
        }
    }
}