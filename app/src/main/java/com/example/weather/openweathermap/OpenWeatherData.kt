package com.example.weather.openweathermap

import com.google.gson.annotations.SerializedName

data class WeatherWrapper(val weather: Array<WeatherData>,
                          val main: MainData)
data class WeatherData(
        val description: String,
        val icon: String)

data class MainData(
        @SerializedName("temp") val temperature: Float,
        val pressure: Int,
        val humidity: Int)