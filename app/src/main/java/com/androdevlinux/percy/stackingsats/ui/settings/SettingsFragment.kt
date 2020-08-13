package com.androdevlinux.percy.stackingsats.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsViewModel.authorizationTokenText.observe(viewLifecycleOwner, Observer {
            edtToken.setText(it)
        })

        settingsViewModel.offerTitleText.observe(viewLifecycleOwner, Observer {
            edtOfferTitle.setText(it)
        })

        settingsViewModel.offerDescriptionText.observe(viewLifecycleOwner, Observer {
            edtOfferDescription.setText(it)
        })

        when (settingsViewModel.appPreferenceManager.onlinePaymentMethodId) {
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

        btnSaveChangesSettings.setOnClickListener {
            if (edtToken.text.toString().isNotEmpty()) {
                settingsViewModel.appPreferenceManager.setAuthorizationToken(edtToken.text.toString())
                showToastySuccess("Changes Updated In System")
            } else {
                showToastyError("Token is empty")
            }

            if (edtOfferTitle.text.toString().isNotEmpty()) {
                settingsViewModel.appPreferenceManager.setOfferTitle(edtOfferTitle.text.toString())
            }

            if (edtOfferDescription.text.toString().isNotEmpty()) {
                settingsViewModel.appPreferenceManager.setOfferDescription(edtOfferDescription.text.toString())
            }

            when {
                onlinePaymentMethodsSpinner.selectedItem.toString() == "Google Pay" -> {
                    settingsViewModel.appPreferenceManager.setOnlinePaymentMethodId(158)
                }
                onlinePaymentMethodsSpinner.selectedItem.toString() == "Paytm" -> {
                    settingsViewModel.appPreferenceManager.setOnlinePaymentMethodId(718)
                }
                onlinePaymentMethodsSpinner.selectedItem.toString() == "UPI Payments" -> {
                    settingsViewModel.appPreferenceManager.setOnlinePaymentMethodId(126)
                }
            }
        }
    }
}