package com.example.home_invest.ui.home.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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
    private val fragments = listOf(
        InvestmentsFragment(),
        InvestmentsFragment(),
        InvestmentsFragment(),
        InvestmentsFragment(),
        InvestmentsFragment()
    )
    private var currentPage = 0

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
        setListeners()
        tintBackButton()
    }

    private fun setView() {
        binding?.initialPageTv?.text = getString(R.string.page_initial)
        binding?.lastPageTv?.text = fragments.size.toString()
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

    private fun setListeners() {
        binding?.backIv?.setOnClickListener {
            binding?.productsVp?.setCurrentItem(currentPage.minus(1), true)
        }
        binding?.nextIv?.setOnClickListener {
            binding?.nextIv?.setOnClickListener {
                binding?.productsVp?.setCurrentItem(currentPage.plus(1), true)
            }
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
                currentPage = position
                if (position == 0) {
                    tintBackButton()
                } else {
                    tintNextButton()
                }
                binding?.indicatorDp?.setProgress(position.plus(1) * 20)
            }
        })
    }

    private fun tintNextButton() {
        context?.let { context ->
            var drawable = AppCompatResources.getDrawable(
                context,
                R.drawable.arrow_left
            )
            drawable = drawable?.let { DrawableCompat.wrap(it) }
            drawable?.let {
                DrawableCompat.setTint(
                    it,
                    ContextCompat.getColor(context, R.color.black)
                )
            }
            binding?.backIv?.setImageDrawable(drawable)
        }
    }

    private fun tintBackButton() {
        context?.let { context ->
            var drawable = AppCompatResources.getDrawable(
                context,
                R.drawable.arrow_left
            )
            drawable = drawable?.let { DrawableCompat.wrap(it) }
            drawable?.let {
                DrawableCompat.setTint(
                    it,
                    ContextCompat.getColor(context, R.color.gray)
                )
            }
            binding?.backIv?.setImageDrawable(drawable)
        }
    }
}
