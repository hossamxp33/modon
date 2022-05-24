package com.tarwej.modon.helper

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.tarwej.modon.databinding.FlightListFragmentBinding

import java.util.*

class Permissions {
    val PERMISSION_ID = 1010

    fun CheckPermission(context: Context): Boolean {
        //true: if we have permission
        //false if not

        if (
            ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(context,android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(context,android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED


        ) {
            return true
        }

        return false

    }

    fun RequestPermission(context: Context) {
        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }





    fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
    var NUM_PAGES = 0
    var currentPage = 0
    var index : Int =  0


     fun init(size: Int?, context: Context, view: FlightListFragmentBinding)  {

        val density = context.getResources().getDisplayMetrics().density

         view.indicator.radius = 4 * density

         if (size != null) {
             NUM_PAGES = size
         } else {             NUM_PAGES = 0
         }
        val handler = Handler()
        val Update:Runnable =Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
        (view).pager?.setCurrentItem(currentPage++, true)
        }

        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 10000)
         view. indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
             override fun onPageSelected(position: Int) {
                 currentPage = position


             }

             override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
             override fun onPageScrollStateChanged(pos: Int) {}
         })

    }

}