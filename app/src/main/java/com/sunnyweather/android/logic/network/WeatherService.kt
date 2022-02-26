package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.DailyResponse
import com.sunnyweather.android.logic.model.LifeIndexResponse
import com.sunnyweather.android.logic.model.RealtimeAQIResponse
import com.sunnyweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("v7/weather/now?key=${SunnyWeatherApplication.KEY}")
    fun getRealtimeWeather(@Query("location") location: String):
            Call<RealtimeResponse>

    @GET("v7/weather/7d?key=${SunnyWeatherApplication.KEY}")
    fun getDailyWeather(@Query("location") location: String):
            Call<DailyResponse>

    @GET("v7/air/now?key=${SunnyWeatherApplication.KEY}")
    fun getRealtimeAQI(@Query("location") location: String):
            Call<RealtimeAQIResponse>

    @GET("v7/indices/1d?key=${SunnyWeatherApplication.KEY}&type=2,3,5,9")
    fun getLifeIndex(@Query("location") location: String):
            Call<LifeIndexResponse>

}