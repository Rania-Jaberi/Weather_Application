package com.example.weather

import android.app.Application

class App : Application() {
    companion object {
        lateinit var instance: App
        val database:Database by lazy {
            Database(instance)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}