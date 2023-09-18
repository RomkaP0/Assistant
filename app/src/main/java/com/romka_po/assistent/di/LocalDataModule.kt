package com.romka_po.assistent.di

import android.content.Context
import com.romka_po.assistent.domain.DatastoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Singleton
    @Provides
    fun provideDatastore(@ApplicationContext context: Context): DatastoreManager {
        return DatastoreManager(context = context)
    }
}