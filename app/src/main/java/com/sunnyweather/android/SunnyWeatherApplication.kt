package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val KEY = "1067042cce67413c8105255b35e0e20e"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}