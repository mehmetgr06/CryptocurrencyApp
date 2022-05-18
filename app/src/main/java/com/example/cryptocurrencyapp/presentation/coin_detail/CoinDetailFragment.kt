package com.example.cryptocurrencyapp.presentation.coin_detail

import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.common.BaseFragment
import com.example.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_coin_detail

}