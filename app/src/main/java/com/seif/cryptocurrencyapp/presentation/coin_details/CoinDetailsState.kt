package com.seif.cryptocurrencyapp.presentation.coin_details

import com.seif.cryptocurrencyapp.domain.model.Coin
import com.seif.cryptocurrencyapp.domain.model.CoinDetails


data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coin: CoinDetails? = null,
    val error: String = ""
)