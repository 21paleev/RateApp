package com.paleevigor.rateapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.paleevigor.rateapp.data.repository.RateRepositoryImpl
import com.paleevigor.rateapp.domain.GetRateInfoUseCase
import com.paleevigor.rateapp.domain.GetRateListUseCase
import com.paleevigor.rateapp.domain.LoadDataUseCase

class RateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RateRepositoryImpl(application)

    private val getRateInfoListUseCase = GetRateListUseCase(repository)
    private val getRateInfoUseCase = GetRateInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val rateInfoList = getRateInfoListUseCase()

    fun getDetailInfo(id: String) = getRateInfoUseCase(id)

    init {
        loadDataUseCase()
    }
}