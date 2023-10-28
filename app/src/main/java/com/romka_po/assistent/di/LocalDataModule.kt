package com.romka_po.assistent.di

import android.content.Context
import androidx.room.Room
import com.romka_po.assistent.domain.local.DatastoreManager
import com.romka_po.assistent.domain.local.room.AppDatabase
import com.romka_po.assistent.domain.local.room.CarMakeDAO
import com.romka_po.assistent.domain.local.room.CarModelDAO
import com.romka_po.assistent.domain.repository.LocalDataLayer
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

    @Singleton
    @Provides
    fun provideLocalDataLayer(carMakeDAO: CarMakeDAO, carModelDAO: CarModelDAO): LocalDataLayer =
        LocalDataLayer(carMakeDAO, carModelDAO)

    @Singleton
    @Provides
    fun provideCarMakesDao(
        database: AppDatabase
    ): CarMakeDAO = database.getMakeDAO()

    @Singleton
    @Provides
    fun provideCarModelsDao(
        database: AppDatabase
    ): CarModelDAO = database.getModelDAO()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "RouteScope.db",
        ).build()
    }
}