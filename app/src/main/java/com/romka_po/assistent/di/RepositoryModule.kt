package com.romka_po.assistent.di

import com.romka_po.assistent.domain.repository.MainRepository
import com.romka_po.assistent.domain.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun provideRepository(impl: MainRepositoryImpl): MainRepository
}