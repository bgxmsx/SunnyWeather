package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class DailyResponse(val code: String, val daily: List<Daily>) {

    data class Daily(val fxDate: String, val tempMax: String, val tempMin: String,
                     @SerializedName("iconDay") val skycon: String)

}
