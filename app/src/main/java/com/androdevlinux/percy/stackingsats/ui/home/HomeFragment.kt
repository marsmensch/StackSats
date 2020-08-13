package com.androdevlinux.percy.stackingsats.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.base.BaseFragment
import com.androdevlinux.percy.stackingsats.services.CreateOfferWorker
import kotlinx.android.synthetic.main.test.*

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.nextDateForOfferCreatingText.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                nextDateOfCreatingOfferLinearLayoutCompat.visibility = View.GONE
                btnCreateFirstOffer.visibility = View.VISIBLE
            } else {
                nextDateOfCreatingOfferLinearLayoutCompat.visibility = View.VISIBLE
                btnCreateFirstOffer.visibility = View.GONE
                nextDateOfCreatingOffer.text = homeViewModel.appPreferenceManager.nextDateForOfferCreating
            }
        })

        btnCreateFirstOffer.setOnClickListener {
            if (!homeViewModel.appPreferenceManager.authorizationToken.isNullOrEmpty() && !homeViewModel.appPreferenceManager.inrAmount.isNullOrEmpty()) {
                val myWorkBuilder = OneTimeWorkRequest.Builder(
                    CreateOfferWorker::class.java
                )
                val myWork = myWorkBuilder.build()
                WorkManager.getInstance(requireContext())
                    .enqueue(myWork)
            } else if (homeViewModel.appPreferenceManager.authorizationToken.isNullOrEmpty()) {
                showToastyError("Authorization Token is empty")
            } else if (homeViewModel.appPreferenceManager.inrAmount.isNullOrEmpty()) {
                showToastyError("Offer amount is empty")
            }
        }
    }
}