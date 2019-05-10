package com.example.mathlearn.app_view

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.example.mathlearn.controller.TotalOperation

private var dialog: AlertDialog? =null




fun  showBadAnswerDialog(fullResult:TotalOperation, context: Context): AlertDialog? {

    dialog= createDialog(fullResult,context)

    dialog!!.show()

    return dialog





}

private  fun createDialog(fullResult: TotalOperation, context: Context): AlertDialog? {


    var dialogbuilder=AlertDialog.Builder(context)
            .setTitle("Wrong Answer")
            .setMessage(fullResult.toString())
            .setPositiveButton("ok",  { dialogInterface, which ->
                Log.i("dialog",dialogInterface.toString())
                dialogInterface.dismiss()
            })

    return dialogbuilder.create()

}
