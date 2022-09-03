package com.seif.cryptocurrencyapp.data.remote

import com.seif.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.seif.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun fetchCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun fetchCoinDetailsById(@Path("coinId") coinId: String): CoinDetailsDto
}