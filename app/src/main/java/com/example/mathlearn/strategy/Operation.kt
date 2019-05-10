package com.example.mathlearn.strategy

class PlusStrategy(override var sign: String="+") :OperationStrategy{
    override fun operations(a: Int, b: Int): Int = a+b


}

class MinusStrategy(override var sign: String="-") :OperationStrategy{
    override fun operations(a: Int, b: Int): Int = a-b


}
class MultipleStrategy(override var sign: String="*") :OperationStrategy{
    override fun operations(a: Int, b: Int): Int = a*b

}
class DivideStrategy(override var sign: String="/") :OperationStrategy {
    override fun operations(a: Int, b: Int): Int = a/b

}