package com.seif.cryptocurrencyapp.di

import com.seif.cryptocurrencyapp.common.Constants
import com.seif.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.seif.cryptocurrencyapp.data.remote.RemoteDataSource
import com.seif.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.seif.cryptocurrencyapp.domain.repository.CoinRepository
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
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(remoteDataSource: RemoteDataSource): CoinRepository {
        return CoinRepositoryImpl(remoteDataSource)
    }
}