package com.paleevigor.rateapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.paleevigor.rateapp.databinding.ActivityRateDetailBinding

class RateDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: RateViewModel
    private var intermediateResult = 0.0

    private val binding by lazy {
        ActivityRateDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_ID)) {
            finish()
            return
        }
        val fromId = intent.getStringExtra(EXTRA_ID) ?: EMPTY_SYMBOL
        viewModel = ViewModelProvider(this)[RateViewModel::class.java]
        viewModel.getDetailInfo(fromId).observe(this) {
            val a: Int = it.nominal ?: 1
            val b: Double = it.value ?: 1.1
            intermediateResult = a / b
            with(binding) {
                tvNameCurrency.text = it.name
                tvNomimal.text = it.nominal.toString()
                tvRateCurrency.text = it.value.toString()
                tvCharCode.text = it.charCode
                tvRateLabel2.text = it.charCode
                binding.btStart.setOnClickListener {
                    binding.tvRateCurrency2.text = calculation()
                }
            }
        }
    }

    private fun calculation(): String {
        var count: String? = binding.etCount.text.toString()
        var countInt = parseCount(count)
        var countValid = validateInput(countInt)
        var countResult = ""
        if (countValid) {
            countResult = (countInt * intermediateResult).toInt().toString()
        } else {
            Toast.makeText(
                applicationContext, "Введите корректную сумму",
                Toast.LENGTH_LONG
            ).show()
        }
        return countResult
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(count: Int): Boolean {
        var result = true
        if (count <= 0) {
            result = false
        }
        return result
    }

    companion object {
        private const val EXTRA_ID = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, id: String): Intent {
            val intent = Intent(context, RateDetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }
}