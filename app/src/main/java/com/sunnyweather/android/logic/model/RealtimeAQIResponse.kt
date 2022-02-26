package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class RealtimeAQIResponse(val code: String,
                               @SerializedName("now") val realtime: Realtime) {

    data class Realtime(val aqi: String)

}
