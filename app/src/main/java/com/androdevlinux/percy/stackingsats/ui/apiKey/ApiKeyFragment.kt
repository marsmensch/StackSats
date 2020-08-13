package com.androdevlinux.percy.stackingsats.ui.apiKey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_api_key.*

class ApiKeyFragment : BaseFragment() {

    private lateinit var apiKeyViewModel: ApiKeyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiKeyViewModel =
            ViewModelProvider(this).get(ApiKeyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_api_key, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiKeyViewModel.authorizationTokenText.observe(viewLifecycleOwner, Observer {
            edtToken.setText(it)
        })

        btnSaveChangesSettings.setOnClickListener {
            if (edtToken.text.toString().isNotEmpty()) {
                apiKeyViewModel.appPreferenceManager.setAuthorizationToken(edtToken.text.toString())
                showToastySuccess("Changes Updated In System")
            } else {
                showToastyError("Token is empty")
            }
        }
    }
}