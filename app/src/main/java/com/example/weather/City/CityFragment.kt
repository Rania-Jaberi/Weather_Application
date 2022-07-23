package com.example.weather.City


import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.weather.App
import com.example.weather.Database
import com.example.weather.R
import androidx.fragment.app.DialogFragment



class CityFragment : Fragment() {
    private lateinit var database: Database
    private lateinit var cities: MutableList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database= App.database
        cities= mutableListOf()
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater?.inflate(R.layout.fragment_city, container,false)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_city, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.actionCreateCity -> {
                showCreateCityDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun showCreateCityDialog() {
        val createCityFragment= CreateCityDialogFragment()
        createCityFragment.listener = object: CreateCityDialogFragment.CreateCityDialogListener{
            override fun onDialogPositiveClick(CityName: String) {
                SaveCity(City(CityName))
            }
            override fun onDialogNegativeClick() {}
        }


    }
    fun SaveCity(city: City){
        if (database.createCity(city)) {
            cities.add(city)
        }else
            Toast.makeText(context,
                "could not create city",
                Toast.LENGTH_LONG).show()


    }




}