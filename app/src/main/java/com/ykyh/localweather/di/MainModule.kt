package com.ykyh.localweather.di

import com.mathpresso.qanda.diV3.ActivityScoped
import com.ykyh.localweather.ui.MainContract
import com.ykyh.localweather.ui.MainPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @ActivityScoped
    @Binds
    abstract fun provideMainPresenter(mainPresenter: MainPresenter) : MainContract.MainPresenter

    @ActivityScoped
    @Binds
    abstract fun provideMainAdapter(adapter: MainAda)

}