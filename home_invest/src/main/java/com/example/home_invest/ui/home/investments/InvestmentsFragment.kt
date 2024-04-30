package com.example.home_invest.ui.home.investments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.home_invest.databinding.FragmentInvestmentsBinding
import com.example.home_invest.ui.extensions.setup
import com.example.home_invest.ui.home.extract.ExtractAdapter
import com.example.network.data.response.ContractedProducts
import com.example.network.data.response.ExtractResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InvestmentsFragment : Fragment() {


    private var binding: FragmentInvestmentsBinding? = null
    private var adapter: InvestmentsAdapter? = null
    private var extractAdapter: ExtractAdapter? = null
    private var viewModel: InvestmentsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvestmentsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = InvestmentsViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[InvestmentsViewModel::class.java]
        binding?.investimentsRv?.isNestedScrollingEnabled = false
        binding?.extractRv?.isNestedScrollingEnabled = false
        setObservables()
        viewModel?.getInvestments()
        viewModel?.getExtract()
    }

    private fun setObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.uiEventInvestments?.collectLatest { event ->
                when (event) {
                    is UiEventInvestments.Success -> {
                        setAdapter(event.investments.contractedProducts)
                    }

                    is UiEventInvestments.Loading -> {
                        // nothing
                    }

                    is UiEventInvestments.Error -> {
                        // nothing
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.uiEventExtract?.collectLatest { event ->
                when (event) {
                    is UiEventExtract.Success -> {
                        setExtractAdapter(event.extract)
                    }

                    is UiEventExtract.Loading -> {
                        // nothing
                    }

                    is UiEventExtract.Error -> {
                        // nothing
                    }
                }
            }
        }
    }

    private fun setAdapter(list: List<ContractedProducts>) {
        adapter = context?.let { InvestmentsAdapter(it) }
        binding?.investimentsRv?.setup(adapter)
        adapter?.list = list
    }

    private fun setExtractAdapter(list: List<ExtractResponse>) {
        extractAdapter = context?.let { ExtractAdapter(it) }
        binding?.extractRv?.setup(extractAdapter)
        extractAdapter?.list = list
    }
}