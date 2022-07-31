package com.example.weather.City

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.App
import com.example.weather.App.Companion.database
import com.example.weather.Database
import com.example.weather.R
import com.example.weather.Utils.toast


open class CityfragmentTest : Fragment(), CityAdapter.CityItemListener {
    private lateinit var database: Database
    private lateinit var cities: MutableList<City>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        cities = mutableListOf()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        recyclerView = view!!.findViewById(R.id.cities_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view

    } //Faire le lien avec le layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cities = database.getAllCities()
        adapter = CityAdapter(cities, this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_city, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //pour la gestion des clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCitySelected(city: City) { }

    override fun onCityDeleted(city: City) {
        showDeleteCityDialog(city)
    }

    private fun showDeleteCityDialog(city: City) {
        val deleteCityFragment = DeleteCityDialogFragment.newInstance(city.name)
        deleteCityFragment.listener = object : DeleteCityDialogFragment.DeleteCityDialogListener {
            override fun onDialogPositiveClick() {
                deleteCity(city)
            }
            override fun onDialogNegativeClick() {}
        }
        deleteCityFragment.show(requireFragmentManager(), "DeleteCityDialogFragment")
    }

    private fun showCreateCityDialog() {

        val createCityFragment = CreateCityDialogFragment()
        createCityFragment.listener = object : CreateCityDialogFragment.CreateCityDialogListener {
            override fun onDialogPositiveClick(CityName: String) {
                saveCity(City(CityName))
            }

            override fun onDialogNegativeClick() {}
        }

        createCityFragment.show(requireFragmentManager(), "CreateCityDialogFragment")
    }

    private fun saveCity(city: City) {
        //l'insertion de bd pour ça on va faire en haut la référence en db
       if(database.createCity(city)){
           cities.add(city)
           Toast.makeText(context, "it is ok", Toast.LENGTH_LONG).show()
       } else
           Toast.makeText(context, "cancel", Toast.LENGTH_LONG).show()
       }



    private fun deleteCity(city: City) {
        if (database.deleteCity(city)){
            cities.remove(city)
            adapter.notifyDataSetChanged()
            Toast.makeText(context, "Deleted!", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(context, "Erreur ! could not delete the City!", Toast.LENGTH_LONG).show()
        }

    }


}





