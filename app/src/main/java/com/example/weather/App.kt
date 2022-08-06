package com.example.weather

import android.app.Application
import com.example.weather.openweathermap.OpenWeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        lateinit var instance: App
        val database:Database by lazy {
            Database(instance)
        }
        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        private val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherService: OpenWeatherService = retrofit.create(OpenWeatherService::class.java)
    }


    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}
//l'interet de creer la bd ici est qu'on pourrait utiliser par plusieurs acitivity