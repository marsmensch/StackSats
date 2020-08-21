package com.androdevlinux.percy.stackingsats.ui.buySettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.base.BaseFragment
import com.ncorti.slidetoact.SlideToActView
import com.ncorti.slidetoact.SlideToActView.OnSlideCompleteListener
import kotlinx.android.synthetic.main.fragment_buy_settings.*


class BuySettingsFragment : BaseFragment() {

    private lateinit var buySettingsViewModel: BuySettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buySettingsViewModel =
            ViewModelProvider(this).get(BuySettingsViewModel::class.java)
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_buy)
    }

    override fun onResume() {
        super.onResume()
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_buy)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buy_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buySettingsViewModel.amountInInrText.observe(viewLifecycleOwner, {
            edtAmountInINR.setText(it)
        })

        if (buySettingsViewModel.appPreferenceManager.weeklyOrMonthly.equals(
                "Weekly",
                ignoreCase = true
            )) {
            weeklyMonthlySpinner.setSelection(0)
            currentConfig.text = getString(R.string.weekly)
        } else if (buySettingsViewModel.appPreferenceManager.weeklyOrMonthly.equals(
                "Monthly",
                ignoreCase = true
            )) {
            weeklyMonthlySpinner.setSelection(1)
            currentConfig.text = getString(R.string.monthly)
        }

        historyCheckTxt.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.nav_buy_order_history)
        }

        btnSaveChanges.onSlideCompleteListener = object : OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                if (edtAmountInINR.text.toString().isNotEmpty()) {
                    buySettingsViewModel.appPreferenceManager.setInrAmount(edtAmountInINR.text.toString())
                    showToastySuccess("Changes Updated In System")
                } else {
                    showToastyError("Amount is empty")
                }

                when {
                    weeklyMonthlySpinner.selectedItem.toString() == "Weekly" -> {
                        buySettingsViewModel.appPreferenceManager.setWeeklyOrMonthly("Weekly")
                        currentConfig.text = getString(R.string.weekly)
                    }
                    weeklyMonthlySpinner.selectedItem.toString() == "Monthly" -> {
                        buySettingsViewModel.appPreferenceManager.setWeeklyOrMonthly("Monthly")
                        currentConfig.text = getString(R.string.monthly)
                    }
                }
                btnSaveChanges.resetSlider()
            }
        }
    }
}