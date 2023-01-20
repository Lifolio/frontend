package com.example.lifolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lifolio.SignUp.IdFragment
import com.example.lifolio.SignUp.PwFragment
import com.example.lifolio.databinding.ActivityIdpwfindBinding
import com.google.android.material.tabs.TabLayout

class IdpwFindActivity :AppCompatActivity() {

    private lateinit var binding : ActivityIdpwfindBinding

    lateinit var idFragment: IdFragment
    lateinit var pwFragment: PwFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdpwfindBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idpwfindBackBtn.setOnClickListener{
            onBackPressed()
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }

        idFragment = IdFragment()
        pwFragment = PwFragment()

        //시작화면 지정
        supportFragmentManager.beginTransaction().add(R.id.frameLayout,idFragment).commit()

        binding.frameTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        //IdFragment
                        replaceView(idFragment)
                    }
                    1->{
                        //PwFragment
                        replaceView(pwFragment)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        //IdFragment
                        replaceView(idFragment)
                    }
                    1->{
                        //PwFragment
                        replaceView(pwFragment)
                    }
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        //IdFragment
                        replaceView(idFragment)
                    }
                    1->{
                        //PwFragment
                        replaceView(pwFragment)
                    }
                }
            }
        })
    }
    private fun replaceView(tab:Fragment){
        //화면 변경
        val selectedFragment:Fragment?
        selectedFragment = tab
        selectedFragment.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, it).commit()
        }
    }

}