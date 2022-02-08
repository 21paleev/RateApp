package com.paleevigor.rateapp.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RateJsonContainerDto(
    @SerializedName("Valute")//Valute
    @Expose
    val json: JsonObject? = null

)