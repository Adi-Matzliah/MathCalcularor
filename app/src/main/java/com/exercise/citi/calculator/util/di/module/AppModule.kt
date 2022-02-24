package com.exercise.citi.calculator.util.di.module

import android.content.Context
import com.exercise.citi.calculator.util.ResourcesLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(
    includes = []
)
object AppModule {

    @Singleton
    @Provides
    fun provideResourcesLoader(@ApplicationContext context: Context) = ResourcesLoader(context)
}