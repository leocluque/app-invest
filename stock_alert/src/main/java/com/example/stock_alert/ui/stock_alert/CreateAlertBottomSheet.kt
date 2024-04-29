package com.example.stock_alert.ui.stock_alert

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.network.data.request.CreateProductAlertRequest
import com.example.network.data.response.ProductStatus
import com.example.stock_alert.R
import com.example.stock_alert.databinding.CreateAlertBottomDialogBinding
import com.example.stock_alert.ui.extensions.formatCurrencyBRL
import com.example.stock_alert.util.CurrencyMask
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateAlertBottomSheet(
    private val productId: String? = null,
    private val productName: String? = null,
    private val productValue: String? = null,
    private val status: ProductStatus? = null,
    private val action: ((CreateProductAlertRequest) -> Unit?)? = null
) : BottomSheetDialogFragment() {

    private var binding: CreateAlertBottomDialogBinding? = null
    private var isAvailable: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateAlertBottomDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    private fun setView() {
        productName?.let {
            binding?.productNameEt?.setText(it)
            binding?.createAlertBtn?.text = getString(R.string.stock_alert_edit_alert)
        }

        productValue?.let {
            binding?.productValueEt?.setText(it)
        }

        status?.let {
            if (it == ProductStatus.AVAILABLE) {
                binding?.availableBtn?.isChecked = true
            } else {
                binding?.notAvailableBtn?.isChecked = true
            }
        }
    }

    private fun setListener() {
        binding?.productValueEt?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (binding?.productValueEt?.text.toString() == "0,00" && p0.toString() == "0,0") {
                    binding?.productValueEt?.setText(
                        0.toFloat().formatCurrencyBRL(true)
                    )
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        binding?.closeIv?.setOnClickListener {
            dismiss()
        }

        binding?.radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            isAvailable = checkedId == R.id.availableBtn
        }

        binding?.createAlertBtn?.setOnClickListener {
            if (isAvailable != null && binding?.productNameEt?.text?.toString()
                    ?.isNotEmpty() == true && binding?.productValueEt?.toString()
                    ?.isNotEmpty() == true
            ) {
                action?.invoke(
                    CreateProductAlertRequest(
                        id  = productId ?: "",
                        productName = binding?.productNameEt?.text.toString(),
                        status = if (isAvailable == true) ProductStatus.AVAILABLE else ProductStatus.NOT_AVAILABLE,
                        productValue = CurrencyMask.unmask(binding?.productValueEt?.text.toString())
                            .toDouble() / 100
                    )
                )
                dismissAllowingStateLoss()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.stock_alert_create_alert_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}