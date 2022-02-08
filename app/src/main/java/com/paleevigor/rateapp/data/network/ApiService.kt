package com.paleevigor.rateapp.data.network

import com.paleevigor.rateapp.data.network.model.RateJsonContainerDto
import retrofit2.http.GET

interface ApiService {
    //@GET("")
    @GET("daily_json.js")
    suspend fun getFullJson(): RateJsonContainerDto
}