package com.paleevigor.rateapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RateInfoDao {
    @Query("SELECT * FROM full_rate_list ORDER BY name ASC")
    fun getPriceList(): LiveData<List<RateInfoDbModel>>

    @Query("SELECT * FROM full_rate_list WHERE id == :id")
    fun getInfoAboutRate(id: String): LiveData<RateInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<RateInfoDbModel>)
}