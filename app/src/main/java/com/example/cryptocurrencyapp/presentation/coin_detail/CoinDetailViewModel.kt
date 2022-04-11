package com.example.cryptocurrencyapp.presentation.coin_detail

import androidx.lifecycle.*
import com.example.cryptocurrencyapp.common.AppConstants.COIN_ID
import com.example.cryptocurrencyapp.common.Result
import com.example.cryptocurrencyapp.domain.use_case.get_coin_detail.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinListLiveData: MutableLiveData<CoinDetailState> = MutableLiveData(CoinDetailState())
    val coinListLiveData: LiveData<CoinDetailState> = _coinListLiveData

    init {
        savedStateHandle.get<String>(COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            getCoinDetailUseCase(coinId).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _coinListLiveData.value = CoinDetailState(coinDetail = result.data)
                    }
                    is Result.Error -> {
                        _coinListLiveData.value =
                            CoinDetailState(errorMessage = result.message ?: "An error occurred")
                    }
                    is Result.Loading -> {
                        _coinListLiveData.value = CoinDetailState(isLoading = true)
                    }
                }
            }
        }
    }

}