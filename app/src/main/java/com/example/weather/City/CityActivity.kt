package com.example.weather


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.City.City
import com.example.weather.City.CityfragmentTest
import com.example.weather.City.DeleteCityDialogFragment
import com.example.weather.Weather.WeatherActivity
import com.example.weather.Weather.WeatherFragment


class CityActivity : AppCompatActivity(), CityfragmentTest.cityFragmentListner {

private lateinit var cityfragmentTest: CityfragmentTest
private var currentCity: City? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)


       cityfragmentTest= supportFragmentManager.findFragmentById(R.id.city_fragment) as CityfragmentTest
        cityfragmentTest.listner= this
        }

    override fun onCitySelected(city: City) {
        currentCity = city
        startWeatherActivity(city)

    }

    private fun startWeatherActivity(city: City) {
       val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(WeatherFragment.EXTRA_CITY_NAME, city.name)
        startActivity(intent)
    }


}







