package com.example.lifolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lifolio.Home.HomeDialog
import com.example.lifolio.My.MyFragment
import com.example.lifolio.databinding.ActivityBnbBinding

class BnbActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBnbBinding

    private lateinit var myFragment: MyFragment
    private lateinit var planningFragment: PlanningFragment
    private lateinit var homeFragment: HomeFragment
    private lateinit var socialFragment: SocialFragment
    private lateinit var portfolioFragment: PortfolioFragment


    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //BNB 액티비티에 다이얼로그 띄우기
        val dialog = HomeDialog(this)
        dialog.homeDlg()

        // 레이아웃과 연결
        //setContentView(R.layout.activity_bnb)
        binding = ActivityBnbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //홈화면을 기본으로 설정
        changeFragment(HomeFragment())

        binding.bnbHomeBtn.setOnClickListener{
            changeFragment(HomeFragment())
        }

        binding.menuBnb.setOnItemSelectedListener() {
            when(it.itemId) {
                R.id.bnb_my -> {
                    changeFragment(MyFragment())
                }
                R.id.bnb_goal -> {
                    changeFragment(PlanningFragment())
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
//        binding.menuBnb.selectedItemId = R.id.bnb_my

    }

    // bnbActivity
    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragments_frame, fragment)
            .commit()
    }

    // profileFragment
    fun changeMyFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragments_frame, ProfileFragment())
            .commit()
    }

    // badgeNewActivity
//    fun changeBadgeFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragments_frame, BadgeFragment())
//            .commit()
//    }
}