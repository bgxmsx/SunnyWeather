package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val code: String, val location: List<Location>)

data class Location(val name: String, @SerializedName("lon") val lng: String, val lat: String,
                    val adm1: String, val adm2: String, val country: String)