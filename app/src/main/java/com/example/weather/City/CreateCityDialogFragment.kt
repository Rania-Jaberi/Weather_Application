package com.example.weather.City

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.weather.R
import java.nio.file.attribute.AclEntry

class CreateCityDialogFragment :DialogFragment() {

    interface CreateCityDialogListener{
        fun onDialogPositiveClick(CityName:String)
        fun onDialogNegativeClick()
    }
    var listener : CreateCityDialogListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder=AlertDialog.Builder(context)
        val input=EditText(context)
        with (input){
            inputType=InputType.TYPE_CLASS_TEXT
            hint=context.getString(R.string.CreateCity_cityhint)
        }
        builder.setTitle(R.string.CreateCity_title)
            .setView(input)
            .setPositiveButton((R.string.CreateCity_positive), DialogInterface.OnClickListener {
                _,_ ->
                listener?.onDialogPositiveClick(input.text.toString())
            })
            .setNegativeButton ((R.string.CreateCity_negative), DialogInterface.OnClickListener{
                dialog,_ ->
                dialog.cancel()
                listener?.onDialogPositiveClick(input.text.toString())
            })

        return builder.create()
    }

}