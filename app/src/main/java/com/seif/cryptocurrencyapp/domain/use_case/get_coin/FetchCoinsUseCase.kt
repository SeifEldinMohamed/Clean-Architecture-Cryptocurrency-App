package com.seif.cryptocurrencyapp.domain.use_case.get_coin

import com.seif.cryptocurrencyapp.common.Resource
import com.seif.cryptocurrencyapp.domain.model.Coin
import com.seif.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Coin>>>{
        return repository.fetchCoins()
    }
}