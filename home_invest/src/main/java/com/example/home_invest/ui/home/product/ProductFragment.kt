package com.example.home_invest.ui.home.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.home_invest.R
import com.example.home_invest.builder.HomeBuilder
import com.example.home_invest.data.model.toProgressItem
import com.example.home_invest.databinding.FragmentProductBinding
import com.example.home_invest.ui.components.CustomViewPager
import com.example.home_invest.ui.extensions.formatCurrencyBRL
import com.example.home_invest.ui.extensions.setVisible
import com.example.home_invest.ui.home.investments.InvestmentsFragment
import com.example.home_invest.ui.home.investments.UiEventInvestments
import com.example.network.data.response.ContractedProducts
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ProductFragment : Fragment() {

    private var binding: FragmentProductBinding? = null
    private var viewPagerAdapter: CustomViewPager? = null
    private var viewModel: ProductViewModel? = null
    private val fragments = listOf(
        InvestmentsFragment(),
        InvestmentsFragment()
    )
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = HomeBuilder.getInvestmentsRepository()?.let { ProductViewModelFactory(it) }
        viewModel = factory?.let { ViewModelProvider(requireActivity(), it) }?.get(ProductViewModel::class.java)
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setView()
        setListeners()
        tintBackButton()
        setObservables()
        viewModel?.getInvestments()

    }

    private fun setObservables() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel?.uiEventInvestments?.collectLatest { event ->
                when (event) {
                    is UiEventInvestments.Success -> {
                        setComponent(
                            event.investments.totalInvested,
                            event.investments.contractedProducts
                        )                    }

                    is UiEventInvestments.Loading -> {
                        //  nothing
                    }

                    is UiEventInvestments.Error -> {
                        setStateViewError()
                    }
                }
            }
        }
    }

    private fun setView() {
        binding?.initialPageTv?.text = getString(R.string.page_initial)
        binding?.lastPageTv?.text = fragments.size.toString()
        binding?.productsVp?.post {
            val layoutParams =  binding?.productsVp?.layoutParams
            layoutParams?.height = binding?.nestedSv?.height?.times(3)
            binding?.productsVp?.layoutParams = layoutParams
        }
    }

    private fun setStateViewError() {
        binding?.apply {
            backIv.setVisible(false)
            initialPageTv.setVisible(false)
            lastPageTv.setVisible(false)
            nextIv.setVisible(false)
            indicatorDp.setVisible(false)
        }
    }

    private fun setComponent(balance: Double, list: List<ContractedProducts>) {
        binding?.circularProgressBar?.configureComponent(
            title = getString(R.string.all_products),
            subtitle = getString(R.string.hundred),
            value = balance.toFloat().formatCurrencyBRL(),
            list = list.map { it.toProgressItem() }
        )
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
        val maxPages = 100 / fragments.size
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
                binding?.indicatorDp?.setProgress(position.plus(1) * maxPages)
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