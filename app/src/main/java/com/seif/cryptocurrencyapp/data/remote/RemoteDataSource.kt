package com.seif.cryptocurrencyapp.data.remote

import com.seif.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.seif.cryptocurrencyapp.data.remote.dto.CoinDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: CoinPaprikaApi
) {
    suspend fun fetchCoins(): List<CoinDto> {
        return api.fetchCoins()
    }

    suspend fun fetchCoinDetailsById(coinId: String): CoinDetailsDto {
        return api.fetchCoinDetailsById(coinId)
    }
}