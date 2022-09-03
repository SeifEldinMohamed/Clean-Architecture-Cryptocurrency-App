package com.seif.cryptocurrencyapp.data.repository

import com.seif.cryptocurrencyapp.common.Resource
import com.seif.cryptocurrencyapp.data.remote.RemoteDataSource
import com.seif.cryptocurrencyapp.domain.repository.CoinRepository
import com.seif.cryptocurrencyapp.domain.toCoin
import com.seif.cryptocurrencyapp.domain.toCoinDetails
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CoinRepository {
    override suspend fun fetchCoins() = flow {
        try {
            emit(Resource.Loading())
            val coins = remoteDataSource.fetchCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }


    override suspend fun fetchCoinDetailsById(coinId: String) = flow {
        try {
            emit(Resource.Loading())
            val coinDetails = remoteDataSource.fetchCoinDetailsById(coinId).toCoinDetails()
            emit(Resource.Success(coinDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}