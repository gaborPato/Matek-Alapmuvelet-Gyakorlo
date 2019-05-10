package com.example.mathlearn.strategy

open interface OperationStrategy {

    var sign:String


    open fun operations(a:Int,b:Int):Int



}