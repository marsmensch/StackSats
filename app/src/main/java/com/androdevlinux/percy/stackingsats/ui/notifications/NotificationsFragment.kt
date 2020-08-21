package com.androdevlinux.percy.stackingsats.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androdevlinux.percy.stackingsats.R

class NotificationsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_notifications)
    }

    override fun onResume() {
        super.onResume()
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_notifications)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}