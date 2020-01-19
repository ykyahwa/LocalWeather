package com.ykyh.localweather.di

import com.ykyh.localweather.LocalWeatherApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<LocalWeatherApplication> {

    override fun inject(instance: LocalWeatherApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: LocalWeatherApplication): Builder

        fun build(): AppComponent

    }
}