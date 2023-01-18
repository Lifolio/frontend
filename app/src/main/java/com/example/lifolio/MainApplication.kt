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

        KakaoSdk.init(this, "c532098877712a29086a2a6e681aab54")
    }
}