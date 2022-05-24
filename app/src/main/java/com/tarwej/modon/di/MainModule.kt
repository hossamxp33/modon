package com.tarwej.modon.di

import androidx.lifecycle.ViewModel

import com.tarwej.modon.helper.ViewModelKey
import com.tarwej.modon.presentation.flightlistfragment.mvi.TripViewModel
import com.tarwej.modon.presentation.homefragment.mvi.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
Created by Prokash Sarkar on Tue, January 19, 2021
 **/

@Module
interface MainModule {
//
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindMainViewModel(mainViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(  TripViewModel::class)
    fun bindTripViewModel(tripViewModel: TripViewModel): ViewModel




}