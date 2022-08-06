package com.example.weather.Weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.weather.App
import com.example.weather.R
import com.example.weather.openweathermap.WeatherWrapper
import com.example.weather.openweathermap.mapOpenWeatherDataToWeather
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class WeatherFragment : Fragment() {


    companion object {
        val EXTRA_CITY_NAME = "training.kotlin.extras.EXTRA_CITY_NAME"
        fun newInstance():WeatherFragment = WeatherFragment() }


    private val TAG = WeatherFragment::class.java
    private lateinit var cityName: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity?.intent!!.hasExtra(EXTRA_CITY_NAME)) {
            updateWeatherFactory(activity?.intent!!.getStringExtra(EXTRA_CITY_NAME))
        }
    }

    private fun updateWeatherFactory(cityName: String?) {
        this.cityName = cityName!!
       val call = App.weatherService.getWeather("$cityName")
        call.enqueue(object : retrofit2.Callback<WeatherWrapper> {
            override fun onResponse(call: Call<WeatherWrapper>, response: Response<WeatherWrapper>) {
                response?.body()?.let{
                   val weather= mapOpenWeatherDataToWeather(it)
                    Log.i(TAG.toString(), "weather response: $weather" )
                }
            }

            override fun onFailure(call: Call<WeatherWrapper>, t: Throwable) {
                Log.e(TAG.toString() , "could not load city weather" , t)
                Toast.makeText(activity, "could not load city weather" , Toast.LENGTH_SHORT).show()
            }

        })

    }


}



