package com.androdevlinux.percy.stackingsats

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.androdevlinux.percy.stackingsats.database.RoomDatabaseHelper
import com.androdevlinux.percy.stackingsats.services.CreateOfferWorker
import com.androdevlinux.percy.stackingsats.services.ListingInProgressContractTimer
import com.androdevlinux.percy.stackingsats.services.NotificationsTimerTask
import com.google.android.material.navigation.NavigationView
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private var timer = Timer()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var doubleBackToExitPressedOnce = false

    companion object {
        var myDatabase: RoomDatabaseHelper? = null
    }

    fun getMyDatabase(): RoomDatabaseHelper? {
        return myDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_buy_order_history, R.id.nav_buy, R.id.nav_api_key, R.id.nav_security, R.id.nav_wallet, R.id.nav_payment_methods, R.id.nav_notifications
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

     //   val myCreateOfferTimerTask = CreateOfferTimer(this@MainActivity)
        val firstStart = 1000 // it means after 1 second.
        val period = 1000 * 60 //after which the task will repeat;(After 60 seconds)
        //timer.schedule(myCreateOfferTimerTask, firstStart.toLong(), period.toLong()) //the time specified in millisecond

        val myWorkBuilder = OneTimeWorkRequest.Builder(
            CreateOfferWorker::class.java
        )
        val myWork = myWorkBuilder.build()
        WorkManager.getInstance(this@MainActivity)
            .enqueue(myWork)

        val myNotificationsTimerTaskTask = NotificationsTimerTask(this@MainActivity)
        val listingInProgressContractTimerTask = ListingInProgressContractTimer(this@MainActivity)
        timer.schedule(myNotificationsTimerTaskTask, firstStart.toLong(), period.toLong()) //the time specified in millisecond
        timer.schedule(listingInProgressContractTimerTask, firstStart.toLong(), period.toLong())

        myDatabase = RoomDatabaseHelper.getInstance(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (navHostFragment?.childFragmentManager?.backStackEntryCount!! > 0) {
            supportFragmentManager.popBackStack()
        } else {
            if (navHostFragment.childFragmentManager.backStackEntryCount == 0) {
                if (doubleBackToExitPressedOnce) {
                    finish()
                } else {
                    Toasty.warning(this, "Press BACK again to EXIT!", Toast.LENGTH_SHORT, true)
                        .show()
                }
                doubleBackToExitPressedOnce = true
                lifecycleScope.launch {
                    delay(2000.toLong())
                    doubleBackToExitPressedOnce = false
                }
            } else {
                super.onBackPressed()
            }
        }
    }
}