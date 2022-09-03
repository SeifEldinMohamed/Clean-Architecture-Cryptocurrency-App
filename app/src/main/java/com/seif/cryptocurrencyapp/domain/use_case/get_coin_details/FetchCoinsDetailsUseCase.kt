package com.seif.cryptocurrencyapp.domain.use_case.get_coin_details

import com.seif.cryptocurrencyapp.common.Resource
import com.seif.cryptocurrencyapp.domain.model.Coin
import com.seif.cryptocurrencyapp.domain.model.CoinDetails
import com.seif.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCoinsDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId:String): Flow<Resource<CoinDetails>>{
        return repository.fetchCoinDetailsById(coinId)
    }
}