package com.androdevlinux.percy.stackingsats

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import io.jmdg.spanly.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController!!.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        // Sample usage
        tvMessage.text =
            Spanly()
                .append(
                    "Stacking Sats",
                    font(Typeface.DEFAULT_BOLD),
                    size(5f),
                    clickable(View.OnClickListener {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }),
                    color(ContextCompat.getColor(this, R.color.colorBlueLight))
                )
        tvMessage.movementMethod = LinkMovementMethod.getInstance()

        val splashTimeOut = 2000
        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */
        lifecycleScope.launch {
            delay(splashTimeOut.toLong())
            // This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            // close this activity
            //finish()
        }

    }

}