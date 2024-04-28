package com.example.home_invest.ui.home.investments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.home_invest.databinding.FragmentInvestmentsBinding
import com.example.home_invest.ui.extensions.setup

class InvestmentsFragment : Fragment() {


    private var binding: FragmentInvestmentsBinding? = null
    private var adapter: InvestmentsAdapter? =  null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvestmentsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        binding?.investimentsRv?.isNestedScrollingEnabled = false
    }


    private fun setAdapter() {
        adapter = context?.let { InvestmentsAdapter(it) }
        binding?.investimentsRv?.setup(adapter)
        adapter?.list = listOf("aaa", "", "", "")
    }
}