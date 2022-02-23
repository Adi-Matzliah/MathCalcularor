package com.exercise.citi.calculator.feature.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CalculatorManagerTest {

    private lateinit var calc: CalculatorManager

    @Before
    fun setUp() {
        calc = CalculatorManager()
    }

    @Test
    fun whenAddingValidNumbers() {
        val number1 = 2
        val number2 = 6
        val result = calc.add(number1, number2).blockingGet()
        assertEquals(result, 8)
    }

    @Test
    fun whenSubtractingValidNumbers() {
        val number1 = 8
        val number2 = 10
        val result = calc.subtract(number1, number2).blockingGet()
        assertEquals(result, -2)
    }

    @Test
    fun whenMultiplyingValidNumbers() {
        val number1 = 7
        val number2 = 5
        val result = calc.multiply(number1, number2).blockingGet()
        assertEquals(result, 35)
    }

    @Test
    fun whenDividingValidNumbers() {
        val number1 = 10
        val number2 = 5
        val result = calc.divide(number1, number2).blockingGet()
        assertEquals(result, 2)
    }
}