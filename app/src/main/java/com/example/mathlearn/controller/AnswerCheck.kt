package com.example.mathlearn.controller

import android.widget.EditText

@Throws( NumberFormatException::class)
fun chkAnswer(number_editTexts: Array<EditText>, totalOperation: TotalOperation):Boolean{
    var answArrn = IntArray(number_editTexts.size)

        for (i in 0 until number_editTexts.size){

            answArrn[i]=number_editTexts[i].text.toString().toInt()

        }

    answArrn.forEachIndexed { i, element ->
        if (element != totalOperation.operation_valueArray[i])
            return false
    }

    return true

}