package com.ykyh.localweather.di

import com.mathpresso.qanda.diV3.ActivityScoped
import com.ykyh.localweather.presentation.main.MainActivity
import com.ykyh.localweather.presentation.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity

}