package com.example.weather.Weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.weather.App
import com.example.weather.R
import com.example.weather.openweathermap.WeatherWrapper
import com.example.weather.openweathermap.mapOpenWeatherDataToWeather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class WeatherFragment : Fragment() {


    companion object {
        val EXTRA_CITY_NAME = "training.kotlin.extras.EXTRA_CITY_NAME"
        fun newInstance(): WeatherFragment = WeatherFragment()
    }


    private val TAG = WeatherFragment::class.java
    private lateinit var cityName: String

    private lateinit var city: TextView
    private lateinit var icon: ImageView
    private lateinit var weatherDescription: TextView
    private lateinit var temperature: TextView
    private lateinit var humidity: TextView
    private lateinit var pressure: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        city = view.findViewById(R.id.city)
        icon = view.findViewById(R.id.weather_icon)
        weatherDescription = view.findViewById(R.id.weather_description)
        temperature = view.findViewById(R.id.temperature)
        humidity = view.findViewById(R.id.humidity)
        pressure = view.findViewById(R.id.pressure)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity?.intent!!.hasExtra(EXTRA_CITY_NAME)) {
            updateWeatherFactory(activity?.intent!!.getStringExtra(EXTRA_CITY_NAME))
        }
    }

    private fun updateWeatherFactory(cityName: String?) {
        this.cityName = cityName!!
        val call = App.weatherService.getWeather("$cityName")
        call.enqueue(object : retrofit2.Callback<WeatherWrapper> {
            override fun onResponse(
                call: Call<WeatherWrapper>,
                response: Response<WeatherWrapper>
            ) {
                response?.body()?.let {
                    val weather = mapOpenWeatherDataToWeather(it)
                    updateUi(weather)
                    Log.i(TAG.toString(), "weather response: $weather")
                }
            }

            override fun onFailure(call: Call<WeatherWrapper>, t: Throwable) {
                Log.e(TAG.toString(), "could not load city weather", t)
                Toast.makeText(activity, "could not load city weather", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun updateUi(weather: Weather) {
        Picasso.get()
            .load(weather.iconUrl)
            .placeholder(R.drawable.ic_baseline_cloud_off_24)
            .into(icon)

        weatherDescription.text = weather.description
        temperature.text = getString(R.string.weather_temperature_value, weather.temperature.toInt())
        humidity.text = getString(R.string.weather_humidity_value, weather.humidity)
        pressure.text= getString(R.string.weather_pressure_value, weather.pressure)
    }


}



