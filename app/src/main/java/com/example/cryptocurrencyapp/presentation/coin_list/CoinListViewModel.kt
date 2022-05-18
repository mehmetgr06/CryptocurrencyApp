package com.example.cryptocurrencyapp.presentation.coin_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocurrencyapp.common.Result
import com.example.cryptocurrencyapp.domain.use_case.get_coin_list.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinListUseCase: GetCoinListUseCase
) : ViewModel() {

    private val _coinListLiveData: MutableLiveData<CoinListState> = MutableLiveData(CoinListState())
    val coinListLiveData: LiveData<CoinListState> = _coinListLiveData

    init {
        getCoinList()
    }

    private fun getCoinList() {
        getCoinListUseCase().onEach { result ->
            when (result) {
                is Result.Success -> {
                    _coinListLiveData.value =
                        CoinListState(coinList = result.data ?: emptyList())
                }
                is Result.Error -> {
                    _coinListLiveData.value =
                        CoinListState(error = result.message ?: "An error occurred")
                }
                is Result.Loading -> {
                    _coinListLiveData.value = CoinListState(isLoading = true)
                }
            }
        }
    }

}