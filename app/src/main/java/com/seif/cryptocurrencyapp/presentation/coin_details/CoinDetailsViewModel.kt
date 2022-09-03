package com.seif.cryptocurrencyapp.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seif.cryptocurrencyapp.common.Constants
import com.seif.cryptocurrencyapp.common.Resource
import com.seif.cryptocurrencyapp.domain.model.CoinDetails
import com.seif.cryptocurrencyapp.domain.use_case.get_coin_details.FetchCoinsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val fetchCoinsDetailsUseCase: FetchCoinsDetailsUseCase,
    savedStateHandle: SavedStateHandle // it's a bundle that contains information about the saved state and contains navigation parameters (for ex: we can use that to restore our app from process death)
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            fetchCoinDetailsById(coinId)
        }
    }

    private fun fetchCoinDetailsById(coinId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchCoinsDetailsUseCase(coinId).onEach { result: Resource<CoinDetails> ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CoinDetailsState(coin = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailsState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CoinDetailsState(isLoading = true)
                    }
                }
            }
        }
    }
}