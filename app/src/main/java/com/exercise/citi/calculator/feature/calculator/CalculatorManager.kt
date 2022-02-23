package com.exercise.citi.calculator.feature.calculator

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class CalculatorManager: IMathOperation {

    override fun add(number1: Int, number2: Int) : Single<Int> = Single.just(number1 + number2).subscribeOn(Schedulers.computation())

    override fun subtract(number1: Int, number2: Int): Single<Int> = Single.just(number1 - number2).subscribeOn(Schedulers.computation())

    override fun multiply(number1: Int, number2: Int): Single<Int> = Single.just(number1 * number2).subscribeOn(Schedulers.computation())

    override fun divide(number1: Int, number2: Int): Single<Int> = Single.just(number1 / number2).subscribeOn(Schedulers.computation())

}