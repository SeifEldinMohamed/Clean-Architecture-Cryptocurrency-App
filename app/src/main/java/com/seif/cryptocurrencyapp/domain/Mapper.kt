package com.seif.cryptocurrencyapp.domain

import com.seif.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.seif.cryptocurrencyapp.data.remote.dto.CoinDto
import com.seif.cryptocurrencyapp.domain.model.Coin
import com.seif.cryptocurrencyapp.domain.model.CoinDetails

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}

fun CoinDetailsDto.toCoinDetails(): CoinDetails {
    return CoinDetails(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team
    )
}