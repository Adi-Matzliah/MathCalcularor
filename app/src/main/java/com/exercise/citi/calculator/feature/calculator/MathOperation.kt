package com.exercise.citi.calculator.feature.calculator

sealed class MathOperation(val number1: Int, val number2: Int) {
    class Plus(number1: Int, number2: Int) : MathOperation(number1, number2)
    class Minus(number1: Int, number2: Int) : MathOperation(number1, number2)
    class Multiplication(number1: Int, number2: Int) : MathOperation(number1, number2)
    class Division(number1: Int, number2: Int) : MathOperation(number1, number2)
}
