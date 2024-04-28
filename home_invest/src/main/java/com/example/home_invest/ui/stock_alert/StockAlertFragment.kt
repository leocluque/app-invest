package com.example.home_invest.ui.stock_alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.home_invest.R
import com.example.home_invest.databinding.FragmentStockAlertBinding
import com.example.home_invest.ui.extensions.setup


class StockAlertFragment : Fragment() {

    private var binding: FragmentStockAlertBinding? = null
    private var adapter: StockAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStockAlertBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setView()
    }

    private fun setView() {
        binding?.stockAlertValueTv?.text = getString(R.string.stock_alert_value, "160.000,00")
    }

    private fun setAdapter() {
        adapter = context?.let { StockAdapter(it) }
        binding?.stockRv?.setup(adapter)
        adapter?.list = listOf("", "", "")
    }

}