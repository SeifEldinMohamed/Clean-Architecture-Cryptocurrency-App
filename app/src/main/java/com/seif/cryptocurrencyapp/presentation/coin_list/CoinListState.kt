package com.seif.cryptocurrencyapp.presentation.coin_list

import com.seif.cryptocurrencyapp.domain.model.Coin


data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)