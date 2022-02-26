package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class RealtimeResponse(val code: String, @SerializedName("now") val realtime: Realtime) {

    data class Realtime(@SerializedName("temp") val temperature: String,
                        @SerializedName("icon") val skycon: String)

}