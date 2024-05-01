package com.example.stock_alert.ui.stock_alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductsResponse
import com.example.stock_alert.R
import com.example.stock_alert.builder.StockAlertBuilder
import com.example.stock_alert.databinding.FragmentStockAlertBinding
import com.example.stock_alert.ui.extensions.formatCurrencyBRL
import com.example.stock_alert.ui.extensions.setVisible
import com.example.stock_alert.ui.extensions.setup
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class StockAlertFragment : Fragment() {

    private var binding: FragmentStockAlertBinding? = null
    private var adapter: StockAdapter? = null
    private var viewModel: StockAlertViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStockAlertBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = StockAlertBuilder.getStockAlertRepository()
            ?.let { StockAlertViewModelFactory(it) }
        viewModel = factory?.let { ViewModelProvider(this, it) }?.get(StockAlertViewModel::class.java)
        setView()
        setListeners()
        setObservables()
        viewModel?.getAllAlerts()
    }

    private fun setObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.uiEvent?.collectLatest { event ->
                when (event) {
                    is UiEventStock.Success -> {
                        setAdapter(event.products)
                    }

                    is UiEventStock.Loading -> {
                        setLoading(event.isLoading)
                    }

                    is UiEventStock.Error -> {
                        Toast.makeText(context, event.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.uiEventBasic?.collectLatest { event ->
                when (event) {
                    is UiEventStockBasic.Success -> {
                        viewModel?.getAllAlerts()
                    }

                    is UiEventStockBasic.Loading -> {
                        setLoading(event.isLoading)
                    }

                    is UiEventStockBasic.Error -> {
                        Toast.makeText(context, event.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setView() {
        binding?.stockAlertValueTv?.text = getString(R.string.stock_alert_value, "160.000,00")
    }

    private fun setLoading(isLoading: Boolean) {
        binding?.stockAlertContentCl?.setVisible(!isLoading)
        binding?.loadingPb?.setVisible(isLoading)
    }

    private fun setAdapter(list: List<ProductsResponse>) {
        binding?.emptyListTv?.setVisible(list.isEmpty())
        adapter = context?.let {
            StockAdapter(it,
                onClickEdit = { editAlert(it) },
                onClickDelete = { deleteAlert(it) })
        }
        binding?.stockRv?.setup(adapter)
        adapter?.list = list
    }

    private fun setListeners() {
        binding?.createAlertLl?.setOnClickListener {
            activity?.supportFragmentManager?.let { it1 ->
                CreateAlertBottomSheet {
                    viewModel?.createAlert(it)
                }.show(it1, "")
            }
        }
    }

    private fun deleteAlert(request: CreateProductAlertRequest) {
        viewModel?.deleteAlert(request)
    }

    private fun editAlert(request: CreateProductAlertRequest) {
        activity?.supportFragmentManager?.let { it1 ->
            CreateAlertBottomSheet(
                request.id,
                request.productName,
                request.productValue.toFloat().formatCurrencyBRL(),
                request.status
            ) {
                viewModel?.updateAlert(it)
            }.show(it1, "")
        }
    }

}