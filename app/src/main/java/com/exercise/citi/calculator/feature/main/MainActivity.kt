package com.exercise.citi.calculator.feature.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.exercise.citi.calculator.R
import com.exercise.citi.calculator.databinding.MainActivityBinding
import com.exercise.citi.calculator.feature.calculator.MathOperation
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var layout: View

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: MainActivityBinding

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        subscribeObservers()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
            layout = root
        }
    }

    private fun subscribeObservers() {
        // plus fields
        disposables.add(Observable.combineLatest(
            binding.input1ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread()),
            binding.input2ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread())
        ) { input1, input2 ->
            mainViewModel.calculate(MathOperation.Plus(if (input1.isNotEmpty()) input1.toInt() else 0 , if (input2.isNotEmpty()) input2.toInt() else 0))
        }
            .subscribe { Timber.i("Observe plus operation") }
        )

        // minus fields
        disposables.add(Observable.combineLatest(
            binding.input3ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread()),
            binding.input4ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread())
        ) { input1, input2 ->
            mainViewModel.calculate(MathOperation.Minus(if (input1.isNotEmpty()) input1.toInt() else 0 , if (input2.isNotEmpty()) input2.toInt() else 0))
        }
            .subscribe { Timber.i("Observe minus operation") }
        )

        // multiplication fields
        disposables.add(Observable.combineLatest(
            binding.input5ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread()),
            binding.input6ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread())
        ) { input1, input2 ->
            mainViewModel.calculate(MathOperation.Multiplication(if (input1.isNotEmpty()) input1.toInt() else 0 , if (input2.isNotEmpty()) input2.toInt() else 0))
        }
            .subscribe { Timber.i("Observe multiplication operation") }
        )

        // division fields
        disposables.add(Observable.combineLatest(
            binding.input7ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread()),
            binding.input8ET.textChanges()
                .debounce(200, TimeUnit.MILLISECONDS)
                .map { c -> c.toString().trim() }
                .observeOn(AndroidSchedulers.mainThread())
        ) { input1, input2 ->
            if (input2.isNotEmpty()) {
                mainViewModel.calculate(
                    MathOperation.Division(
                        if (input1.isNotEmpty()) input1.toInt() else 0,
                        input2.toInt()
                    )
                )
            }
        }
            .subscribe { Timber.i("Observe division operation") }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

}