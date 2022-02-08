package com.paleevigor.rateapp.domain

class GetRateListUseCase(
    private val repository: RateRepository
) {
    operator fun invoke() = repository.getRateList()
}