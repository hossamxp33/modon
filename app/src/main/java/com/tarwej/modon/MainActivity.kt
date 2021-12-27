package com.tarwej.modon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.tarwej.modon.presentation.flightlistfragment.FlightListFragment
import com.tarwej.modon.presentation.homefragment.HomeFragment
import com.tarwej.modon.presentation.homefragment.mvi.HomeViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() , HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var fragmentFactory : FragmentFactory


    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


  //      supportFragmentManager.fragmentFactory = fragmentFactory

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(0, 0,0, 0)
            .replace(R.id.main_frame, HomeFragment())
            .addToBackStack(null).commit()
    }

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}