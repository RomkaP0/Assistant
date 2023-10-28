package com.romka_po.assistent.di

import android.content.Context
import com.romka_po.assistent.domain.api.BackendAPI
import com.romka_po.assistent.domain.api.CarNetworkSource
import com.romka_po.assistent.domain.repository.NetworkDataLayer
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Reusable
    fun backendAPIService(
//        okHttpClient: OkHttpClient,
    ): BackendAPI {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
            .build()
            .create(BackendAPI::class.java)
    }
    @Singleton
    @Provides
    fun provideCarSource(@ApplicationContext context:Context) = CarNetworkSource(context = context)

    @Singleton
    @Provides
    fun provideNetworkDataLayer(backendAPI: BackendAPI): NetworkDataLayer =
        NetworkDataLayer(backendAPI)
}