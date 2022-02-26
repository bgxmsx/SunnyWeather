package com.sunnyweather.android.logic.model

data class DailyAQIResponse(val code: String, val daily: List<Daily>) {

    data class Daily(val aqi: String)

}
