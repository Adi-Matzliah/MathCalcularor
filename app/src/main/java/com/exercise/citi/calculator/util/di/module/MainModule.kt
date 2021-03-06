package com.exercise.citi.calculator.util.di.module

import com.exercise.citi.calculator.feature.calculator.CalculatorManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
object MainModule {
    @Provides
    @ActivityRetainedScoped
    internal fun provideCalculatorManager() = CalculatorManager()
}