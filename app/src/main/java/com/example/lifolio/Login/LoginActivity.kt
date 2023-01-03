package com.example.lifolio.Login

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lifolio.Home.HomeActivity
import com.example.lifolio.R
import com.example.lifolio.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 버튼
        binding.navBottom.setOnClickListener{
            // editText 로부터 입력된 값을 받아온다
            val id = binding.editId.text.toString()
            val pw = binding.editPw.text.toString()

            if(id == "" || pw == "") {
                // 회원정보 입력 전부 안 되어 있으면
                Toast.makeText(this, "회원정보를 전부 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "${id}", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "${pw}", Toast.LENGTH_SHORT).show()
                // 쉐어드로부터 저장된 id, pw 가져오기
                val sharedPreference = getSharedPreferences("user", Context.MODE_PRIVATE)
                val savedId = sharedPreference.getString("id", "")
                val savedPw = sharedPreference.getString("pw", "")

                // 유저가 입력한 id, pw 값과 쉐어드로 불러온 id, pw값 비교
                if(id == savedId && pw == savedPw){
                    // 로그인 성공

                }
                else{
                    // 로그인 실패
                }
            }

        }

        // 카카오 소셜 로그인
        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
                setContentView(binding.root)}
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val kakao_login_button = binding.kakao // 로그인 버튼

        kakao_login_button.setOnClickListener {
            if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

        // 네이버 소셜 로그인
        val naver_login_button = binding.naver // 로그인 버튼

        naver_login_button.setOnClickListener {
            val oAuthLoginCallback = object : OAuthLoginCallback {
                override fun onSuccess() {
                    // 네이버 로그인 API 호출 성공 시 유저 정보를 가져온다
                    NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                        override fun onSuccess(result: NidProfileResponse) {
                            val nickname = result.profile?.nickname.toString()

                            Log.e(TAG, "네이버 로그인한 유저 정보 - 이름 : $nickname")

//                            val intent = Intent(this, HomeActivity::class.java)
//                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                            finish()
                        }

                        override fun onError(errorCode: Int, message: String) {
                            //
                        }

                        override fun onFailure(httpStatus: Int, message: String) {
                            //
                        }
                    })
                }

                override fun onError(errorCode: Int, message: String) {
                    val naverAccessToken = NaverIdLoginSDK.getAccessToken()
                    Log.e(TAG, "naverAccessToken : $naverAccessToken")
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    //
                }
            }

            NaverIdLoginSDK.initialize(this@LoginActivity, getString(R.string.naver_client_id), getString(
                R.string.naver_client_secret
            ), "앱 이름")
            NaverIdLoginSDK.authenticate(this@LoginActivity, oAuthLoginCallback)

        }
    }

}