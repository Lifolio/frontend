package com.example.lifolio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lifolio.Login.LoginActivity
import com.example.lifolio.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var bnbActivity: BnbActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //배지 더보기 클릭 시 배지 화면으로 이동
        binding.btnGotoBadges.setOnClickListener {
            val intent = Intent(getActivity(), BadgeNewActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        // 회원 탈퇴
        binding.withdrawal.setOnClickListener{
            val intent = Intent(getActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            activity?.finish()
        }

        // 로그아웃
        binding.logout.setOnClickListener {
            val intent = Intent(getActivity(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            activity?.finish()
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentProfileBinding.inflate(layoutInflater)

    }
}