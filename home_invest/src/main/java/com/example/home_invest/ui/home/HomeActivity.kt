package com.example.home_invest.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.home_invest.R
import com.example.home_invest.databinding.ActivityHomeBinding
import com.example.home_invest.ui.components.CustomViewPager
import com.example.home_invest.ui.home.product.ProductFragment
import com.example.home_invest.ui.home.wallets.WalletsFragment

class HomeActivity : AppCompatActivity() {

    internal lateinit var binding: ActivityHomeBinding
    private val fragments = listOf(ProductFragment(), WalletsFragment())

    private val viewPagerAdapter by lazy {
        val viewPagerAdapter = CustomViewPager(supportFragmentManager, this)
        binding.pagesVp.adapter = viewPagerAdapter
        viewPagerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
        setListeners()
    }

    internal fun setView() {
        viewPagerAdapter.addFrag(fragments)
        binding.balanceValueTv.text = getString(R.string.balance, "160.000,00")
    }

    private fun setListeners() {
        binding.tabs.setWalletState {
            binding.pagesVp.setCurrentItem(1, true)
        }
        binding.tabs.setProductState {
            binding.pagesVp.setCurrentItem(0, true)
        }
        binding.pagesVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
                binding.tabs.onVpScroll(position)
            }
        })
    }
}