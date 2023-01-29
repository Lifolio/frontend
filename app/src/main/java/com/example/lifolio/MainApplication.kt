package com.example.lifolio

import android.app.Application
import com.example.lifolio.JWT.PreferenceUtil
import com.kakao.sdk.common.KakaoSdk

class MainApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)

        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}