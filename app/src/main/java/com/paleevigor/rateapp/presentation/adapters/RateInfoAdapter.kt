package com.paleevigor.rateapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.paleevigor.rateapp.databinding.ItemRateInfoBinding
import com.paleevigor.rateapp.domain.RateInfo

class RateInfoAdapter(
    private val context: Context
) : ListAdapter<RateInfo, RateInfoViewHolder>(CoinInfoDiffCallback) {

    var onRateClickListener: OnRateClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateInfoViewHolder {
        val binding = ItemRateInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RateInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RateInfoViewHolder, position: Int) {
        val rate = getItem(position)
        with(holder.binding) {
            with(rate) {
                tvName.text = name
                tvRate.text = value.toString()
                root.setOnClickListener {
                    onRateClickListener?.onRateClick(this)
                }
            }
        }
    }

    interface OnRateClickListener {
        fun onRateClick(coinPriceInfo: RateInfo)
    }
}