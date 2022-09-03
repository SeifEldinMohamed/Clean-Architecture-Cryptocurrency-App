package com.seif.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seif.cryptocurrencyapp.common.Resource
import com.seif.cryptocurrencyapp.domain.model.Coin
import com.seif.cryptocurrencyapp.domain.use_case.get_coin.FetchCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val fetchCoinsUseCase: FetchCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        fetchCoins()
    }

    private fun fetchCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchCoinsUseCase().onEach { result: Resource<List<Coin>> ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CoinListState(coins = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = CoinListState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CoinListState(isLoading = true)
                    }
                }
            }
        }
    }
}