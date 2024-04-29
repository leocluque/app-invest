package com.example.home_invest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.home_invest.databinding.FragmentHomeBinding
import com.example.home_invest.ui.components.CustomViewPager
import com.example.home_invest.ui.extensions.formatCurrencyBRL
import com.example.home_invest.ui.extensions.setVisible
import com.example.home_invest.ui.home.product.ProductFragment
import com.example.home_invest.ui.home.wallets.WalletsFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var viewPagerAdapter: CustomViewPager? = null
    private val homeViewModel: HomeViewModel by lazy {
        val factory = HomeViewModelFactory()
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setAdapter()
        setView()
        setObservables()
        homeViewModel.getBalance()
    }

    private fun setObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEventHome.Success -> {
                        setBalance(event.balance.balance.toFloat().formatCurrencyBRL())
                    }

                    is UiEventHome.Loading -> {
                        setLoading(event.isLoading)
                    }

                    is UiEventHome.Error -> {
                        Toast.makeText(context, event.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    internal fun setView() {
        val fragments = listOf(ProductFragment(), WalletsFragment())
        viewPagerAdapter?.addFrag(fragments)
    }

    private fun setBalance(balance: String) {
        binding?.balanceValueTv?.text = balance
    }

    private fun setLoading(isLoading: Boolean) {
        binding?.loadingPb?.setVisible(isLoading)
        binding?.homeContentCl?.setVisible(!isLoading)
    }

    private fun setAdapter() {
        viewPagerAdapter = activity?.supportFragmentManager?.let {
            CustomViewPager(
                it,
                context
            )
        }
        binding?.pagesVp?.adapter = viewPagerAdapter
    }

    private fun setListeners() {
        binding?.apply {

            tabs.setWalletState {
                pagesVp.setCurrentItem(1, true)
            }
            tabs.setProductState {
                pagesVp.setCurrentItem(0, true)
            }
            pagesVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                    // nothing
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    // nothing
                }

                override fun onPageSelected(position: Int) {
                    tabs.onVpScroll(position)
                }
            })
        }

    }
}