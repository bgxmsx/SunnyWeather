package com.sunnyweather.android.logic.model

data class LifeIndexResponse(val code: String, val daily: List<Daily>) {

    data class Daily(val type: String, val category: String)

}
