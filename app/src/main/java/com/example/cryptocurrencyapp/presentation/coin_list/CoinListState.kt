package com.example.cryptocurrencyapp.presentation.coin_list

import com.example.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    var isLoading: Boolean = false,
    var coinList: List<Coin> = emptyList(),
    var error: String = ""
)
