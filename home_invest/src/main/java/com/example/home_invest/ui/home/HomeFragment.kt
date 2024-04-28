package com.example.home_invest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.home_invest.R
import com.example.home_invest.databinding.FragmentHomeBinding
import com.example.home_invest.ui.components.CustomViewPager
import com.example.home_invest.ui.home.product.ProductFragment
import com.example.home_invest.ui.home.wallets.WalletsFragment

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var viewPagerAdapter: CustomViewPager? = null

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
    }

    internal fun setView() {
        val fragments = listOf(ProductFragment(), WalletsFragment())
        viewPagerAdapter?.addFrag(fragments)
        binding?.balanceValueTv?.text = getString(R.string.balance, "160.000,00")
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