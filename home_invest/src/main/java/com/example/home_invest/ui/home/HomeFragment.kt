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

    var binding: FragmentHomeBinding? = null
    var viewPagerAdapter: CustomViewPager? = null
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = HomeViewModelFactory()
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
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

    fun setObservables() {
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

    fun setBalance(balance: String) {
        binding?.balanceValueTv?.text = balance
    }

    fun setLoading(isLoading: Boolean) {
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

    fun setListeners() {
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