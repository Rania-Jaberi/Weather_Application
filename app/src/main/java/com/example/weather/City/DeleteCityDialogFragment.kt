package com.example.weather.City

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.weather.R


class DeleteCityDialogFragment : DialogFragment() {


   interface DeleteCityDialogListener {
       fun onDialogPositiveClick()
       fun onDialogNegativeClick()
   }
    //on utilise la notion de Bundle mais on ne va pas la utiliser en extra mais plutot en argument

    companion object {
        val EXTRA_CITY_NAME = "training.kotlin.weather.extras.EXTRA_CITY_NAME"
        fun newInstance(cityName: String): DeleteCityDialogFragment {
            val fragment = DeleteCityDialogFragment()
            fragment.arguments= Bundle().apply {
                putString(EXTRA_CITY_NAME, cityName)
            }
            return fragment
        }
    }
    var listener: DeleteCityDialogListener? = null
    private lateinit var cityName: String



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        cityName = arguments?.getString(EXTRA_CITY_NAME)!!
    }

   override  fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
       val builder= AlertDialog.Builder(context)
       builder.setTitle(getString(R.string.deletecity_title, cityName))
           .setPositiveButton("Delete city",
               { _, _ -> listener?.onDialogPositiveClick()  })
           .setNegativeButton("cancel",
               {_,_ -> listener?.onDialogNegativeClick()})

       return builder.create()
   }
}

