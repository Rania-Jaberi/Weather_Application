package com.example.weather

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.nfc.Tag
import android.util.Log
import com.example.weather.City.City

private const val DATABASE_NAME="weather.db" // le nom du fichier dans lequel toute la db sera stocké
private const val DATABASE_VERSION =1

private const val CITY_TABLE_NAME="City"
private const val CITY_KEY_ID="id"
private const val CITY_kEY_NAME="name"

private const val CITY_TABLE_CREATE="""
CREATE TABLE $CITY_TABLE_NAME(
           $CITY_KEY_ID INTEGER PRIMARY KEY,
           $CITY_kEY_NAME TEXT)
"""
private const val CITY_QUERY_SELECT_ALL ="SELECT * FROM $CITY_TABLE_NAME"

// Creation de la requete create table


class Database (context:Context)
    : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    val TAG = Database::class.java.simpleName

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CITY_TABLE_CREATE) //Utilsation de la requete
    }
    // a cet etape on a créer notre bd

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun createCity(city: City): Boolean{
        val values = ContentValues()
        values.put(CITY_kEY_NAME,city.name)
    Log.d(TAG, "Creating city: $values")
        val id= writableDatabase.insert(CITY_TABLE_NAME,null,values)
        city.id=id
        return id >0

    }

    @SuppressLint("Range")
    fun getAllCities(): MutableList<City> {
        val cities = mutableListOf<City>()

        readableDatabase.rawQuery(CITY_QUERY_SELECT_ALL, null).use{ cursor ->
            while (cursor.moveToNext()){
                val city= City (
                    cursor.getLong(cursor.getColumnIndex(CITY_KEY_ID)) ,
                    cursor.getString(cursor.getColumnIndex(CITY_kEY_NAME))


                        )
                cities.add(city)

            }
        }

        return cities
    }
}