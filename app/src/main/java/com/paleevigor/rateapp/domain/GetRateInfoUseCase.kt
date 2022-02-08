package com.paleevigor.rateapp.domain

class GetRateInfoUseCase(
    private val repository: RateRepository
) {
    operator fun invoke(id: String) = repository.getRateInfo(id)
}