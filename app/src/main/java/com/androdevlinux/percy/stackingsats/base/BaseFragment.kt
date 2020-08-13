package com.androdevlinux.percy.stackingsats.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty

open class BaseFragment : Fragment() {
    fun showToastySuccess(text: String?) {
        Toasty.success(requireContext(), text!!, Toast.LENGTH_SHORT, true).show()
    }

    fun showToastyError(text: String?) {
        Toasty.error(requireContext(), text!!, Toast.LENGTH_SHORT, true).show()
    }

    fun showToastyWarning(text: String?) {
        Toasty.warning(requireContext(), text!!, Toast.LENGTH_SHORT, true).show()
    }

    fun showToastyInfo(text: String?) {
        Toasty.info(requireContext(), text!!, Toast.LENGTH_SHORT, true).show()
    }
}