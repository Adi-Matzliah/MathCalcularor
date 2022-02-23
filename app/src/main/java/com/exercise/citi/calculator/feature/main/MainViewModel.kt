package com.exercise.citi.calculator.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.citi.calculator.feature.calculator.CalculatorManager
import com.exercise.citi.calculator.feature.calculator.MathOperation
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val calculatorManager: CalculatorManager
) : ViewModel() {

    lateinit var disposable: Disposable
    private val compositeDisposables = CompositeDisposable()

    private val _plusResult = MutableLiveData<Int>()
    val plusResult: LiveData<Int>
        get() = _plusResult

    private val _minusResult = MutableLiveData<Int>()
    val minusResult: LiveData<Int>
        get() = _minusResult

    private val _multipleResult = MutableLiveData<Int>()
    val multiplicationResult: LiveData<Int>
        get() = _multipleResult

    private val _divisionResult = MutableLiveData<Int>()
    val divisionResult: LiveData<Int>
        get() = _divisionResult

    fun calculate(mathOperation: MathOperation) {
        val number1 = mathOperation.number1
        val number2 = mathOperation.number2

        Timber.d("Math operation ${mathOperation.javaClass.simpleName}: input1: $number1, input2: $number2")
        when (mathOperation) {
            is MathOperation.Plus -> {
                disposable = calculatorManager.add(number1, number2)
                    .subscribe { result ->
                        Timber.d("$number1 + $number2  = $result")
                        _plusResult.postValue(result)
                    }
            }
            is MathOperation.Minus -> {
                disposable = calculatorManager.subtract(number1, number2)
                    .subscribe { result ->
                        Timber.d("$number1 - $number2  = $result")
                         _minusResult.postValue(result)
                    }
            }

            is MathOperation.Multiplication -> {
                disposable = calculatorManager.multiply(number1, number2)
                    .subscribe { result ->
                        Timber.d("$number1 * $number2  = $result")
                        _multipleResult.postValue(result)
                    }
            }

            is MathOperation.Division -> {
                disposable = calculatorManager.divide(number1, number2)
                    .subscribe { result ->
                        Timber.d("$number1 / $number2  = $result")
                         _divisionResult.postValue(result)
                    }
            }
        }
        compositeDisposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposables.clear()
    }
}