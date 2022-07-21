package com.example.weather

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.nfc.Tag
import android.util.Log
import com.example.weather.City.City

private const val DATABASE_NAME="weather.db"
private const val version =1

private const val CITY_TABLE_NAME="City"
private const val CITY_KEY_ID="id"
private const val CITY_kEY_NAME="name"

private const val CITY_TABLE_CREATE="""
CREATE TABLE $CITY_TABLE_NAME(
           $CITY_KEY_ID INTEGER PRIMARY KEY,
           $CITY_TABLE_NAME TEXT
)
"""


class Database (context:Context): SQLiteOpenHelper(context, DATABASE_NAME,null, version){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CITY_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun createCity(city: City): Boolean{
        val values = ContentValues()
        values.put(CITY_TABLE_NAME,city.name)
    Log.d(TAG,"create city: $values")
        val id= writableDatabase.insert(CITY_TABLE_NAME,null,values)
        city.id=id
        return id >0

    }
}