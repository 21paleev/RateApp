package com.paleevigor.rateapp.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_rate_list")
data class RateInfoDto(
    @PrimaryKey
    @SerializedName("ID")
    @Expose
    val id: String,
    @SerializedName("NumCode")
    @Expose
    val numCode: String?,
    @SerializedName("CharCode")
    @Expose
    val charCode: String?,
    @SerializedName("Nominal")
    @Expose
    val nominal: Int?,
    @SerializedName("Name")
    @Expose
    val name: String?,
    @SerializedName("Value")
    @Expose
    val value: Double?,
    @SerializedName("Previous")
    @Expose
    val previous: Double?
)