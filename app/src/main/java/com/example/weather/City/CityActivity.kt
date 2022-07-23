package com.example.weather


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.weather.City.CreateCityDialogFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_main)

        var createCityFragment = CreateCityDialogFragment()

        createCityFragment.show(supportFragmentManager,"CreateCityDialogFragment")

    }







}