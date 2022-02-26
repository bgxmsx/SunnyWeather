package com.sunnyweather.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.dao.PlaceDao
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Weather
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if (placeResponse.code == "200") {
            val places = placeResponse.location
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response code is ${placeResponse.code}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        Log.d("TestTestTest", "$lng $lat")
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val deferredRealtimeAQI = async {
                SunnyWeatherNetwork.getRealtimeAQI(lng, lat)
            }
            val deferredLifeIndex = async {
                SunnyWeatherNetwork.getLifeIndex(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            val realtimeAQIResponse = deferredRealtimeAQI.await()
            val lifeIndexResponse = deferredLifeIndex.await()
            Log.d("TestTestTest", "${realtimeResponse.code} ${dailyResponse.code} " +
                    "${realtimeAQIResponse.code} ${lifeIndexResponse.code}")
            if (realtimeResponse.code == "200" && dailyResponse.code == "200" &&
                realtimeAQIResponse.code == "200" && lifeIndexResponse.code == "200") {
                val weather = Weather(realtimeResponse.realtime, dailyResponse,
                    realtimeAQIResponse.realtime.aqi, lifeIndexResponse)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtime response code is ${realtimeResponse
                    .code}, daily response code is ${dailyResponse.code}, realtime aqi response " +
                        "code is ${realtimeAQIResponse.code}, life index response code " +
                        "is ${lifeIndexResponse.code}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = liveData(context) {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result)
    }

    fun savePlace(place: Location) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

}