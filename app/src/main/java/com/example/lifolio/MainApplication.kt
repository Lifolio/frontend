package com.example.lifolio

import android.app.Application
import com.example.lifolio.JWT.TokenSharedPreferences

class App:Application() {
    companion object{
        lateinit var token_prefs : TokenSharedPreferences
    }

    override fun onCreate() {
        token_prefs = TokenSharedPreferences(applicationContext)
        super.onCreate()
    }
}