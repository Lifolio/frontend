package com.example.lifolio

import android.app.Application
import com.example.lifolio.JWT.PreferenceUtil

class MainApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)
    }
}