package com.example.lifolio.Login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var checkId = ""
    private var checkPw = ""

    // 서버 연결
    object RequestToServer {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://www.lifolio.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 버튼
        binding.backBtn.setOnClickListener {
            onBackPressed()
            overridePendingTransition(0,0) // 화면 전환시 매끄럽게 넘어가게 하는 코드
        }

        // 디폴트로 로그인 버튼 비활성화
        binding.btnLogin.isEnabled = false

        // 아이디 활성화
        binding.editId.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkId = binding.editId.text.toString()
                binding.btnLogin.isEnabled = !(checkId == "" || checkPw == "")
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        // 비밀번호 활성화
        binding.editPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkPw = binding.editPw.text.toString()
                binding.btnLogin.isEnabled = !(checkId == "" || checkPw == "")
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        // 로그인 버튼
        binding.btnLogin.setOnClickListener{
            val requestToServer = RequestToServer // 서버 요청

            if(binding.editId.text.isNullOrBlank() || binding.editPw.text.isNullOrBlank()) {
                // 회원정보 입력 전부 안 되어 있으면
                Toast.makeText(this, "회원정보를 전부 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                requestToServer.apiService.requestLogin(
                    RequestLogin(
                        username = binding.editId.text.toString(),
                        password = binding.editPw.text.toString()
                    ) // 로그인 정보 전달
                ).enqueue(object : Callback<ResponseLogin> {
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        Log.d("통신 실패", "${t.message }")
                    }
                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {
                        if (response.isSuccessful) {
                            val responseData = response.body()
                            if (responseData!!.isSuccess) {
                                Log.d("성공", response.body()!!.result.toString())
                                var myjwt = response.body()!!.result!!.accessToken
                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                finish()
                            } else if(responseData.code == 2027){
                                Toast.makeText(this@LoginActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                            } else if (responseData.code == 2024){
                                Toast.makeText(this@LoginActivity, "존재하지 않는 유저입니다.", Toast.LENGTH_SHORT).show()
                            } else if (responseData.code == 2010){
                                Toast.makeText(this@LoginActivity, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
                            } else if (responseData.code == 2011){
                                Toast.makeText(this@LoginActivity, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                            } else if (responseData.code == 4000){
                                Toast.makeText(this@LoginActivity, "데이터베이스에러", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
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
                setContentView(binding.root)
            }
        }
        // 로그인 됐는지 callback 함수
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
        // 결과값 들고온 카카오 로그인 버튼
        binding.kakao.setOnClickListener {
            if(LoginClient.instance.isKakaoTalkLoginAvailable(this)){
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else{
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }

        // 네이버 소셜 로그인
        binding.naver.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            val oAuthLoginCallback = object : OAuthLoginCallback {
                override fun onSuccess() {
                    // 네이버 로그인 API 호출 성공 시 유저 정보를 가져온다
                    NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                        override fun onSuccess(result: NidProfileResponse) {
                            val nickname = result.profile?.nickname.toString()
                            Log.e(TAG, "네이버 로그인한 유저 정보 - 이름 : $nickname")
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                            finish()
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
            // 네이버 소셜 로그인 초기화
            NaverIdLoginSDK.initialize(this@LoginActivity, getString(R.string.naver_client_id), getString(
                R.string.naver_client_secret
            ), "앱 이름")
            NaverIdLoginSDK.authenticate(this@LoginActivity, oAuthLoginCallback)

        }
    }
}