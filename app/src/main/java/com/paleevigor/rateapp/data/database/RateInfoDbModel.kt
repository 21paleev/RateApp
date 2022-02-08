package com.paleevigor.rateapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_rate_list")
data class RateInfoDbModel(
    @PrimaryKey
    val id: String,
    val numCode: String?,
    val charCode: String?,
    val nominal: Int?,
    val name: String?,
    val value: Double?,
    val previous: Double?
)
