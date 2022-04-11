package com.example.cryptocurrencyapp.domain.use_case.get_coin_list

import com.example.cryptocurrencyapp.common.Result
import com.example.cryptocurrencyapp.data.remote.dto.toCoin
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke(): Flow<Result<List<Coin>>> = flow {
        try {
            emit(Result.Loading())
            val coins = coinRepository.getCoins().map { it.toCoin() }
            emit(Result.Success(coins))
        } catch (e: HttpException) {
            emit(Result.Error(errorMessage = e.localizedMessage ?: "An error has occurred"))
        }
    }

}