package com.sunnyweather.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://devapi.qweather.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)


    private const val BASE_URL_GEO = "https://geoapi.qweather.com/"

    private val geoRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GEO)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> geoCreate(serviceClass: Class<T>): T = geoRetrofit.create(serviceClass)

    inline fun <reified T> geoCreate(): T = geoCreate(T::class.java)

}