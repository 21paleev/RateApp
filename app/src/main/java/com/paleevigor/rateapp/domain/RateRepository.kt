package com.paleevigor.rateapp.domain

import androidx.lifecycle.LiveData

interface RateRepository {

    fun getRateList(): LiveData<List<RateInfo>>

    fun getRateInfo(id: String): LiveData<RateInfo>

    fun loadData()
}