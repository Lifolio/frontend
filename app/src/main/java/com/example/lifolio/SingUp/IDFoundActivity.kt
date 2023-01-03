package com.example.lifolio.SingUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.Login.LoginActivity
import com.example.lifolio.databinding.ActivityIdfoundBinding

class IDFoundActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdfoundBinding // binding 변수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // '로그인 하기' 버튼 클릭하면 로그인 화면으로 넘어감
        binding.idfoundGotologinBtn.setOnClickListener {
            gotoLogin()
        }
    }

    private fun gotoLogin() { // 로그인 화면으로 가기 함수
        startActivity(Intent(this, LoginActivity::class.java))
    }
}


// Fragment로 만들었을 때 쓴 코드
// override fun onCreateView(
// inflater: LayoutInflater,
// container: ViewGroup?,
// savedInstanceState: Bundle?
// ): View? { // binding 적용
// binding = FragmentIdfoundBinding.inflate(layoutInflater, container, false)
//
// // '로그인 하기' 버튼 클릭하면 로그인 화면으로 넘어감
// binding.idfoundGotologinBtn.setOnClickListener {
// gotoLogin()
// }
//
// return binding.root
// }
//
// private fun gotoLogin() { // 로그인 화면으로 가기 함수
// startActivity(Intent(requireActivity(), LoginActivity::class.java))
// }