package com.example.lifolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lifolio.databinding.ActivityBnbBinding
import com.example.lifolio.R

class BnbActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBnbBinding

    private lateinit var myFragment: MyFragment
    private lateinit var goalFragment: GoalFragment
    private lateinit var socialFragment: SocialFragment
    private lateinit var portfolioFragment: PortfolioFragment


    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 레이아웃과 연결
        //setContentView(R.layout.activity_bnb)
        binding = ActivityBnbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menuBnb.setOnItemSelectedListener() {
            when(it.itemId) {
                R.id.bnb_my -> {
                    changeFragment(MyFragment())
                }
                R.id.bnb_goal -> {
                    changeFragment(GoalFragment())
                }
                R.id.bnb_social -> {
                    changeFragment(SocialFragment())
                }
                R.id.bnb_portfolio -> {
                    changeFragment(PortfolioFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.menuBnb.selectedItemId = R.id.bnb_my


    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragments_frame, fragment)
            .commit()
    }
}