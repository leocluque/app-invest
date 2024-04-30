package com.example.home_invest.ui.home.investments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.home_invest.builder.HomeBuilder
import com.example.home_invest.databinding.FragmentInvestmentsBinding
import com.example.home_invest.ui.extensions.setup
import com.example.home_invest.ui.home.extract.ExtractAdapter
import com.example.home_invest.ui.home.product.ProductViewModel
import com.example.home_invest.ui.home.product.ProductViewModelFactory
import com.example.network.data.response.ContractedProducts
import com.example.network.data.response.ExtractResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InvestmentsFragment : Fragment() {


    private var binding: FragmentInvestmentsBinding? = null
    internal var adapter: InvestmentsAdapter? = null
    internal var extractAdapter: ExtractAdapter? = null
    internal var viewModel: InvestmentsViewModel? = null
    internal var productViewModel: ProductViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvestmentsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = HomeBuilder.getInvestmentsRepository()?.let { ProductViewModelFactory(it) }
        productViewModel =
            factory?.let { ViewModelProvider(requireActivity(), it) }?.get(ProductViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = HomeBuilder.getExtractRepository()?.let { InvestmentsViewModelFactory(it) }
        viewModel = factory?.let { ViewModelProvider(this, it) }?.get(InvestmentsViewModel::class.java)
        binding?.investimentsRv?.isNestedScrollingEnabled = false
        binding?.extractRv?.isNestedScrollingEnabled = false
        setObservables()
        viewModel?.getExtract()
    }

    private fun setObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel?.uiEventInvestments?.observeForever() { event ->
                when (event) {
                    is UiEventInvestments.Success -> {
                        setAdapter(event.investments.contractedProducts)
                    }

                    is UiEventInvestments.Loading -> {
                        //  nothing
                    }

                    is UiEventInvestments.Error -> {
                        //  nothing
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

   internal fun setAdapter(list: List<ContractedProducts>) {
        adapter = context?.let { InvestmentsAdapter(it) }
        binding?.investimentsRv?.setup(adapter)
        adapter?.list = list
    }

    internal fun setExtractAdapter(list: List<ExtractResponse>) {
        extractAdapter = context?.let { ExtractAdapter(it) }
        binding?.extractRv?.setup(extractAdapter)
        extractAdapter?.list = list
    }
}