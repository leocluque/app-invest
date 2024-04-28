package com.example.home_invest.ui.home.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.home_invest.R
import com.example.home_invest.databinding.FragmentProductBinding
import com.example.home_invest.ui.components.CustomViewPager
import com.example.home_invest.ui.components.ProgressItem
import com.example.home_invest.ui.home.investments.InvestmentsFragment


class ProductFragment : Fragment() {

    private var binding: FragmentProductBinding? = null
    private var viewPagerAdapter: CustomViewPager? = null
    private val fragments = listOf(InvestmentsFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setView()
    }

    private fun setView() {
        binding?.circularProgressBar?.configureComponent(
            subtitle = "100%",
            value = getString(R.string.balance, "160.000,00")
        )
        context?.let { context ->
            binding?.circularProgressBar?.setList(
                listOf(
                    ProgressItem(60f, context.getColor(R.color.blue)),
                    ProgressItem(20f, context.getColor(R.color.red)),
                    ProgressItem(20f, context.getColor(R.color.green))
                )
            )
        }
    }

    private fun setViewPager() {
        viewPagerAdapter = CustomViewPager(childFragmentManager, context)
        viewPagerAdapter?.addFrag(fragments)
        binding?.productsVp?.adapter = viewPagerAdapter
        binding?.productsVp?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
                binding?.indicatorDp?.setProgress(position * 10)
            }
        })
    }
}
