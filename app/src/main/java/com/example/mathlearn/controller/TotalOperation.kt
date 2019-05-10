package com.example.mathlearn.controller

import com.example.mathlearn.strategy.*

import kotlin.random.Random

class TotalOperation(var limit: Int = 10,val fullMode:Boolean=false) {

    var strategy: OperationStrategy? = null
        private set
     var a: Int? = null
       private set
     var b: Int? = null
        private set
     var opResult: Int? = null
        private set
    var operation_valueArray=IntArray(3)
        private set

    init {

        strategy=createStrategy()

        createOperationValues()

        createValueArray()





    }

    private fun createValueArray() {

        operation_valueArray[0]= a!!
        operation_valueArray[1]= b!!
        operation_valueArray[2]= opResult!!
    }

    private fun createOperationValues() {

        a = Random.nextInt(2, limit)
        b = Random.nextInt(1, limit)

        when (strategy) {
            is MinusStrategy -> b = Random.nextInt(1, a!!)
            is DivideStrategy ->
                while (a!! % b!! != 0) {
                    b = Random.nextInt(1, a!!)
                }
        }
        opResult= strategy!!.operations(a!!,b!!)
    }

    private fun createStrategy(): OperationStrategy? {
        var strategyList =
                mutableListOf<OperationStrategy>(PlusStrategy(), MinusStrategy())
        if (fullMode){
            strategyList.add(MultipleStrategy())
            strategyList.add(DivideStrategy())
        }

       return strategyList[Random.nextInt(strategyList.size)]
    }

    override fun toString(): String {
        return "$a ${strategy!!.sign} $b = $opResult"
    }

}