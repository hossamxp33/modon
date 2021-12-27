package com.tarwej.modon

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tarwej.modon.presentation.flightlistfragment.FlightListFragment


class ClickHandler {


    //var Pref: PreferenceHelper? = null
    var context: Context? = null

//    fun SwitchToCities(context: Context) {
//        val i = Intent(context, CitiesActivity::class.java)
//        (context as MainActivity).startActivityForResult(i, 1000);
//    }

//    fun Switch_to_Register_Activity(context: Context) {
//        val homeIntent = Intent(context, RegisterActivity()::class.java)
//        (context as MainActivity).startActivity(homeIntent)
//    }

//    fun OpenMyAdressFragment(context: Context) {
//        val frag = AddressBottomFragment()
//        frag.apply {
//            show((context as MainActivity).supportFragmentManager, AddressBottomFragment.TAG)
//        }
    //  }


    fun flightListFragment(context: Context, cityName: String) {
        (context as MainActivity)

        val fragment = FlightListFragment()
        val bundle = Bundle()
        bundle.putString("cityName", cityName)
        fragment.arguments = bundle
        switchBetweenFragments(context, fragment)
    }


//    fun logout(context: Context) {
//        context as MainActivity
//        context.Pref.UserId = 0
//        context.Pref.token = ""
//        context.Pref.userName = ""
//
//        val i = Intent(context, RegisterActivity::class.java)
//        (context).startActivity(i);
//
//    }

    fun switchToActivity(context: Context, activity: Activity) {
        val i = Intent(context, activity::class.java)
        (context).startActivity(i);

    }

    fun switchBetweenFragments(context: Context, fragment: Fragment) {
        (context as MainActivity)
        context.supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_right, 0, 0, R.anim.slide_left)
            .replace(R.id.main_frame, fragment).addToBackStack(null).commit()
    }

    fun slideDownFragments(context: Context, fragment: Fragment) {
        (context as MainActivity)
        context.supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_top, 0, 0, R.anim.slide_down)
            .replace(R.id.main_frame, fragment).addToBackStack(null).commit()
    }


}


