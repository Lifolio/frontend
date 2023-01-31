package com.example.lifolio

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.util.Utility

import com.example.lifolio.JWT.PreferenceUtil
import com.kakao.sdk.common.KakaoSdk

class MainApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)

        var keyHash = Utility.getKeyHash(this)
        Log.d("keyHash 값 ", "keyHash :$keyHash")



        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)

    }


}