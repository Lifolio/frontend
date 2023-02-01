package com.example.lifolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lifolio.CustomLifolio.CustomLifolioActivity
import com.example.lifolio.CustomOfTheYear.CustomOfTheYearActivity
import com.example.lifolio.EditCategory.EditCategoryActivity
import com.example.lifolio.Login.LoginActivity
import com.example.lifolio.OneRecord.OneRecordActivity
import com.example.lifolio.SignUp.TermsOfServiceActivity
import com.example.lifolio.ViewAllMyLifolio.ViewAllLifolioActivity
import com.example.lifolio.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (task.isSuccessful) {
                }
                if (!task.isSuccessful) {
                    Log.w("FCM Log", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result?:""
                MainApplication.prefs.setString("fcmToken",token)
                Log.d("FCM Log", "FCM 토큰: $token")
            })

        // 자동 로그인 pref
        val pref = getSharedPreferences("username", 0)
        val savedUsername = pref.getString("username", "").toString()

        // 로그인 버튼
        binding.btnLogin.setOnClickListener {
            if (savedUsername.equals("")) { // 자동 로그인 아니라면
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else { // 자동 로그인 경우
                val intent = Intent(this, BnbActivity::class.java)
                startActivity(intent)
                Log.d("username", savedUsername)
                Toast.makeText(this, "로그인 하였습니다", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // 허니가 개발중인 Activity 미리보기 위한 버튼
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, TermsOfServiceActivity::class.java)
            startActivity(intent)
        }


        // 챠코 테스트 할 때만 임시로 쓰겠습니다
        /*
        binding.btnId.setOnClickListener {
            val intent = Intent(this, IDFoundActivity::class.java)
            startActivity(intent)
        }
        */
//        setGetFun()
    }
//    okHttp3 되는지 테스트
//    private fun setGetFun() {
//        val url = "https://www.naver.com"
//        val okHttpClient = OkHttpClient();
//        val request = Request.Builder().url(url).build()
//
//        okHttpClient.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {}
//
//            override fun onResponse(call: Call, response: Response) {
//                Log.d("RESPONSE", response.body!!.string())
//            }
//        })
//    }
}