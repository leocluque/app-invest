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
            ),
            list = listOf(
                ProgressItem("1",20f, "#f28500"),
                ProgressItem("2",20f, "#7A124E"),
                ProgressItem("3",20f, "#5786B9"),
                ProgressItem("4",20f, "#FFBB86FC"),
                ProgressItem("5",20f, "#FF6200EE")
            )
        )
    }
}