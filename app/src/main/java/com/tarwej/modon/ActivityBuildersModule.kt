package com.tarwej.modon

import com.example.dagger.di.scopes.ActivityScope

import com.tarwej.modon.MainActivity
import com.tarwej.modon.di.MainModule
import com.tarwej.modon.helper.FragmentFactoryModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
Created by Prokash Sarkar on Tue, January 19, 2021
 **/

@Module
interface ActivityBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class, FragmentFactoryModule::class])
    fun contributeMainActivity(): MainActivity




}