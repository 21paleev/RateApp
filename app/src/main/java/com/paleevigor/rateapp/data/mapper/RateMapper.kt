package com.paleevigor.rateapp.data.mapper

import com.google.gson.Gson
import com.paleevigor.rateapp.data.database.RateInfoDbModel
import com.paleevigor.rateapp.data.network.model.RateInfoDto
import com.paleevigor.rateapp.data.network.model.RateJsonContainerDto
import com.paleevigor.rateapp.domain.RateInfo

class RateMapper {
    fun mapDtoToDbModel(dto: RateInfoDto) = RateInfoDbModel(
        id = dto.id,
        numCode = dto.numCode,
        charCode = dto.charCode,
        nominal = dto.nominal,
        name = dto.name,
        value = dto.value,
        previous = dto.previous
    )

    fun mapJsonContainerToListRateInfo(jsonContainer: RateJsonContainerDto): List<RateInfoDto> {
        val result = mutableListOf<RateInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val priceInfo = Gson().fromJson(
                currencyJson, RateInfoDto::class.java
            )
            result.add(priceInfo)
        }
        return result
    }

    fun mapDbModelToEntity(dbModel: RateInfoDbModel) = RateInfo(
        id = dbModel.id,
        numCode = dbModel.numCode,
        charCode = dbModel.charCode,
        nominal = dbModel.nominal,
        name = dbModel.name,
        value = dbModel.value,
        previous = dbModel.previous
    )


}