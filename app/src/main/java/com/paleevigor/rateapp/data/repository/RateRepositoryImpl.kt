package com.paleevigor.rateapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.paleevigor.rateapp.data.database.AppDatabase
import com.paleevigor.rateapp.data.mapper.RateMapper
import com.paleevigor.rateapp.data.workers.RefreshDataWorker
import com.paleevigor.rateapp.domain.RateInfo
import com.paleevigor.rateapp.domain.RateRepository

class RateRepositoryImpl(
    private val application: Application
) : RateRepository {

    private val rateInfoDao = AppDatabase.getInstance(application).ratePriceInfoDao()
    private val mapper = RateMapper()

    override fun getRateList(): LiveData<List<RateInfo>> {
        return Transformations.map(rateInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getRateInfo(id: String): LiveData<RateInfo> {
        return Transformations.map(rateInfoDao.getInfoAboutRate(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}