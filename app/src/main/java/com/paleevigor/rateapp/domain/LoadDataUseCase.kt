package com.paleevigor.rateapp.domain

class LoadDataUseCase(
    private val repository: RateRepository
) {
    operator fun invoke() = repository.loadData()
}