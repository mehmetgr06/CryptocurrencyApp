package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.common.AppConstants.BASE_URL
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }

}