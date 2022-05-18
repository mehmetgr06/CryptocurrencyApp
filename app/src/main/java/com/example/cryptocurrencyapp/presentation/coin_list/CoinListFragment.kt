package com.example.cryptocurrencyapp.presentation.coin_list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.common.BaseFragment
import com.example.cryptocurrencyapp.databinding.FragmentCoinListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : BaseFragment<FragmentCoinListBinding>() {

    private val coinListViewModel by viewModels<CoinListViewModel>()

    private val coinListAdapter = CoinListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        with(coinListViewModel) {
            coinListLiveData.observe(viewLifecycleOwner) { coinState ->
                coinListAdapter.submitList(coinState.coinList)
                binding.rvCoinList.adapter = coinListAdapter
                if (coinState.error.isNotBlank()) {
                    Toast.makeText(requireContext(), coinState.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_coin_list

}