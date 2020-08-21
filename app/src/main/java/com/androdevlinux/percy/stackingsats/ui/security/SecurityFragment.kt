package com.androdevlinux.percy.stackingsats.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androdevlinux.percy.stackingsats.R
import com.androdevlinux.percy.stackingsats.utils.FormValidator
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_security.*

class SecurityFragment : Fragment(), View.OnClickListener {
    private var currentPinInput = ""
    private var initPin = ""
    private var pinLength = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_security)
    }

    override fun onResume() {
        super.onResume()
        (context as AppCompatActivity).supportActionBar!!.title = requireContext().getString(R.string.title_security)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_security, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pin_check_btn.setOnClickListener{
            if (validateFields()) {

            }
        }
        pinLength = 4
        pin_verification_code.setMaxLength(pinLength)

        pin_one.setOnClickListener(this)
        pin_two.setOnClickListener(this)
        pin_three.setOnClickListener(this)
        pin_four.setOnClickListener(this)
        pin_five.setOnClickListener(this)
        pin_six.setOnClickListener(this)
        pin_seven.setOnClickListener(this)
        pin_eight.setOnClickListener(this)
        pin_nine.setOnClickListener(this)
        pin_zero.setOnClickListener(this)
        pin_cancel_btn.setOnClickListener(this)
        pin_eight.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.pin_one -> {
                currentPinInput += "1"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_two -> {
                currentPinInput += "2"
                pin_verification_code.setText(currentPinInput)
            }
            R.id.pin_three -> {
                currentPinInput += "3"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_four -> {
                currentPinInput += "4"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_five -> {
                currentPinInput += "5"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_six -> {
                currentPinInput += "6"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_seven -> {
                currentPinInput += "7"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_eight -> {
                currentPinInput += "8"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_nine -> {
                currentPinInput += "9"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_zero -> {
                currentPinInput += "0"
                pin_verification_code.setText(currentPinInput)

            }
            R.id.pin_cancel_btn -> {
                currentPinInput = ""
                pin_verification_code.setText(initPin)
            }
        }
    }

    private fun validateFields() : Boolean {
        val formValidator = FormValidator(requireContext())

        val pinValidator = formValidator.validatePin(pin_verification_code.text.toString(), pinLength)
        if (!pinValidator.isFormValid()) {
            pin_verification_code.error = pinValidator.responseMessage
            Toasty.error(requireContext(), pin_verification_code.error, Toast.LENGTH_SHORT, true).show()
        }

        return formValidator.isFormValid
    }
}