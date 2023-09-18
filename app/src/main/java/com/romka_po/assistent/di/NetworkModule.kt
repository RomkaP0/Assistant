package com.romka_po.assistent.di

import android.content.Context
import com.romka_po.assistent.domain.api.CarNetworkSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideCarSource(@ApplicationContext context:Context) = CarNetworkSource(context = context)
}