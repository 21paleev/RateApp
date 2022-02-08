package com.paleevigor.rateapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.paleevigor.rateapp.domain.RateInfo

object CoinInfoDiffCallback : DiffUtil.ItemCallback<RateInfo>() {
    override fun areItemsTheSame(oldItem: RateInfo, newItem: RateInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RateInfo, newItem: RateInfo): Boolean {
        return oldItem == newItem
    }
}