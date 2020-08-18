package com.androdevlinux.percy.stackingsats.ui.paymentMethods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.base.BaseFragment
import com.androdevlinux.percy.stackingsats.utils.AppPreferenceManager
import com.ncorti.slidetoact.SlideToActView
import kotlinx.android.synthetic.main.fragment_payment_methods.*

class PaymentMethodsFragment : BaseFragment() {

    private lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appPreferenceManager = AppPreferenceManager(requireContext())
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_payment_methods)
    }

    override fun onResume() {
        super.onResume()
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_payment_methods)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_methods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (appPreferenceManager.onlinePaymentMethodId) {
            158 -> {
                onlinePaymentMethodsSpinner.setSelection(0)
            }
            718 -> {
                onlinePaymentMethodsSpinner.setSelection(1)
            }
            126 -> {
                onlinePaymentMethodsSpinner.setSelection(2)
            }
        }

        btnSaveChanges.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                when {
                    onlinePaymentMethodsSpinner.selectedItem.toString() == "Google Pay" -> {
                        appPreferenceManager.setOnlinePaymentMethodId(158)
                    }
                    onlinePaymentMethodsSpinner.selectedItem.toString() == "Paytm" -> {
                        appPreferenceManager.setOnlinePaymentMethodId(718)
                    }
                    onlinePaymentMethodsSpinner.selectedItem.toString() == "UPI Payments" -> {
                        appPreferenceManager.setOnlinePaymentMethodId(126)
                    }
                }
                showToastySuccess("Changes Updated In System")
                btnSaveChanges.resetSlider()
            }
        }
    }
}