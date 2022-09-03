package com.seif.cryptocurrencyapp.domain.repository

import com.seif.cryptocurrencyapp.common.Resource
import com.seif.cryptocurrencyapp.domain.model.Coin
import com.seif.cryptocurrencyapp.domain.model.CoinDetails
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun fetchCoins(): Flow<Resource<List<Coin>>>
    suspend fun fetchCoinDetailsById(coinId:String): Flow<Resource<CoinDetails>>
}