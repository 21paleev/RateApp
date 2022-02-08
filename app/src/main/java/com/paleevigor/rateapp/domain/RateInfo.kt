package com.paleevigor.rateapp.domain

data class RateInfo(
    val id: String,
    val numCode: String?,
    val charCode: String?,
    val nominal: Int?,
    val name: String?,
    val value: Double?,
    val previous: Double?
)