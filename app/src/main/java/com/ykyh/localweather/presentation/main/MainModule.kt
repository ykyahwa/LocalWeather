package com.ykyh.localweather.presentation.main

import com.ykyh.localweather.di.ActivityScoped
import com.ykyh.localweather.data.MainItem
import com.ykyh.localweather.network.WeatherApi
import com.ykyh.localweather.presentation.base.BaseRecyclerViewAdapter
import com.ykyh.localweather.repository.WeatherRepository
import com.ykyh.localweather.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class MainModule {

    @ActivityScoped
    @Binds
    abstract fun provideMainPresenter(mainPresenter: MainPresenter) : MainContract.MainPresenter

    @ActivityScoped
    @Binds
    abstract fun provideMainAdapter(adapter: MainAdapter) : BaseRecyclerViewAdapter<MainItem>

    @ActivityScoped
    @Binds
    abstract fun provideWeatherRepository(weatherRepository: WeatherRepositoryImpl) : WeatherRepository


    @Module
    companion object {

        @ActivityScoped
        @JvmStatic
        @Provides
        fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
    }

}