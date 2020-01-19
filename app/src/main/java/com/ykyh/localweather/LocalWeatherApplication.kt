package com.ykyh.localweather

import com.ykyh.localweather.di.AppComponent
import com.ykyh.localweather.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class LocalWeatherApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
        DaggerAppComponent.builder().application(this).build()
//
//    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? =
//        DaggerAppComponent.builder().application(this).build()

}