package com.tarwej.modon.helper


import com.tarwej.modon.di.AppComponent
import com.tarwej.modon.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import java.net.URISyntaxException
import java.util.stream.DoubleStream.builder
import java.util.stream.Stream.builder

/**
Created by Prokash Sarkar on Tue, January 19, 2021
 **/

open class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return initializeDaggerComponent()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        appComponent = DaggerAppComponent.factory()
            .create(this)

    }

    open fun initializeDaggerComponent(): AppComponent {
        val component: AppComponent = DaggerAppComponent.factory()
            .create(this)

        component.inject(this)

        return component
    }


}