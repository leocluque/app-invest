package com.example.home_invest.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.home_invest.R
import com.example.home_invest.databinding.CustomTabLayoutBinding
import com.example.home_invest.ui.extensions.setVisible

class TabBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var itemView: CustomTabLayoutBinding? = null
    private var productState: (() -> Unit)? = null
    private var walletState: (() -> Unit)? = null

    init {
        itemView = CustomTabLayoutBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.DefaultTabBar, 0, 0)

            val tabOne = typedArray.getString(R.styleable.DefaultTabBar_tabOne)
            val tabTwo = typedArray.getString(R.styleable.DefaultTabBar_tabTwo)

            itemView?.productsBtn?.text = tabOne
            itemView?.walletsBtn?.text = tabTwo
            itemView?.productsBtn?.setOnClickListener {
                onClickProducts()
            }
            itemView?.walletsBtn?.setOnClickListener {
                onClickWallet()
            }
            typedArray.recycle()
        }
    }

    private fun onClickProducts() {
        itemView?.lineProduct?.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.in_from_left
            )
        )
        itemView?.lineWallet?.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.out_to_left
            )
        )

        productState?.invoke()
        itemView?.productsBtn?.setTextColor(ContextCompat.getColor(context, R.color.black))

        itemView?.lineWallet?.setVisible(visible = false, useInvisible = true)
        itemView?.lineProduct?.setVisible(true)
        itemView?.walletsBtn?.setTextColor(ContextCompat.getColor(context, R.color.gray))
    }

    private fun onClickWallet(
    ) {
        itemView?.lineWallet?.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.in_from_right
            )
        )
        itemView?.lineProduct?.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.out_to_right
            )
        )

        walletState?.invoke()
        itemView?.walletsBtn?.setTextColor(ContextCompat.getColor(context, R.color.black))

        itemView?.lineProduct?.setVisible(visible = false, useInvisible = true)
        itemView?.lineWallet?.setVisible(true)
        itemView?.productsBtn?.setTextColor(ContextCompat.getColor(context, R.color.gray))
    }

    fun setProductState(action: (() -> Unit)) {
        productState = action
    }

    fun setWalletState(action: (() -> Unit)) {
        walletState = action
    }

    fun onVpScroll(position: Int) {
        when (position) {
            0 -> onClickProducts()
            1 -> onClickWallet()
        }
    }
}