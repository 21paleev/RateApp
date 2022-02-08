package com.paleevigor.rateapp.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.paleevigor.rateapp.data.database.AppDatabase
import com.paleevigor.rateapp.data.mapper.RateMapper
import com.paleevigor.rateapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val rateInfoDao = AppDatabase.getInstance(context).ratePriceInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = RateMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val jsonContainer = apiService.getFullJson()
                val rateInfoDtoList = mapper.mapJsonContainerToListRateInfo(jsonContainer)
                val dbModelList = rateInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                rateInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    companion object {

        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}