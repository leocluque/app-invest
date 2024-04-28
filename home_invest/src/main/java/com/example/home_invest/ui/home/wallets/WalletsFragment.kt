package com.example.home_invest.ui.home.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.home_invest.R
import com.example.home_invest.databinding.FragmentWalletsBinding
import com.example.home_invest.ui.components.ProgressItem

class WalletsFragment : Fragment() {


    private var binding: FragmentWalletsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWalletsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        binding?.circularProgressBar?.configureComponent(
            "Abril 2024", "Total Investido", getString(
                R.string.balance, "160.000,00"
            )
        )
        context?.let { context ->
            binding?.circularProgressBar?.setList(
                listOf(
                    ProgressItem(20f, context.getColor(R.color.orange)),
                    ProgressItem(20f, context.getColor(R.color.red)),
                    ProgressItem(20f, context.getColor(R.color.blue)),
                    ProgressItem(20f, context.getColor(R.color.purple_200)),
                    ProgressItem(20f, context.getColor(R.color.purple_500))
                )
            )
        }
    }
}