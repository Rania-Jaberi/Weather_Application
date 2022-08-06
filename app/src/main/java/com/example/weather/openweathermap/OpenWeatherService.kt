package com.example.weather.openweathermap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "584ca5db5736376bb29f96da377987ba"

interface OpenWeatherService {
    @GET("data/2.5/weather?units=metric&lang=fr")
    fun getWeather(
        @Query("q") ciyName: String,
        @Query("appid") apiKey: String = API_KEY
    ): Call<WeatherWrapper>
}