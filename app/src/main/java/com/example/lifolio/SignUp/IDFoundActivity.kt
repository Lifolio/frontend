package com.example.lifolio.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifolio.Login.LoginActivity
import com.example.lifolio.databinding.ActivityIdfoundBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IDFoundActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdfoundBinding // binding 변수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdfoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrofit Build (서버 연동)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.lifolio.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()                                           // API 웹 브라우저 창 열기
        val idRequest = retrofit.create(IDRequest::class.java) // API 요청 (주소 입력)

        val extras = intent.extras
        val userId = extras!!["userId"] as String

        // 서버에서 받은 id로 채우기
        binding.userid.text = userId

        // 'X' 버튼 클릭하면 화면 닫힘 > ID/PW 찾기 화면으로 돌아감
        binding.idfoundCloseBtn.setOnClickListener {
            gotoIDFound()
        }

        // '로그인 하기' 버튼 클릭하면 로그인 화면으로 넘어감
        binding.idfoundGotologinBtn.setOnClickListener {
            gotoLogin()
        }
    }

    private fun gotoIDFound() { // ID/PW 찾기 화면으로 돌아가기 (ID/PW 찾기 화면은 그대로 유지되어 있어야 함)
        onBackPressed()
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