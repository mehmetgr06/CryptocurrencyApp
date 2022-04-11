package com.example.cryptocurrencyapp.domain.use_case.get_coin_detail

import com.example.cryptocurrencyapp.common.Result
import com.example.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyapp.domain.model.CoinDetail
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    suspend operator fun invoke(coinId: String): Flow<Result<CoinDetail>> = flow {
        try {
            emit(Result.Loading())
            val coinDetail = coinRepository.getCoinById(coinId).toCoinDetail()
            emit(Result.Success(coinDetail))
        } catch (e: HttpException) {
            emit(Result.Error(errorMessage = e.localizedMessage ?: "An error has occurred"))
        }
    }

}