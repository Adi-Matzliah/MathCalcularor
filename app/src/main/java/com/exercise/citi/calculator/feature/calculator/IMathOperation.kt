package com.exercise.citi.calculator.feature.calculator

import io.reactivex.rxjava3.core.Single

interface IMathOperation {
    fun add(number1: Int, number2: Int): Single<Int>
    fun subtract(number1: Int, number2: Int): Single<Int>
    fun multiply(number1: Int, number2: Int): Single<Int>
    fun divide(number1: Int, number2: Int): Single<Int>
}