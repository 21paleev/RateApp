package com.paleevigor.rateapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.paleevigor.rateapp.databinding.ActivityRateListBinding
import com.paleevigor.rateapp.domain.RateInfo
import com.paleevigor.rateapp.presentation.adapters.RateInfoAdapter

class RateListActivity : AppCompatActivity() {
    private lateinit var viewModel: RateViewModel

    private val binding by lazy {
        ActivityRateListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = RateInfoAdapter(this)
        adapter.onRateClickListener = object : RateInfoAdapter.OnRateClickListener {
            override fun onRateClick(rateinfo: RateInfo) {
                val intent = RateDetailActivity.newIntent(
                    this@RateListActivity,
                    rateinfo.id
                )
                startActivity(intent)
            }
        }
        binding.rvRateList.adapter = adapter
        binding.rvRateList.itemAnimator = null
        viewModel = ViewModelProvider(this)[RateViewModel::class.java]
        viewModel.rateInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}