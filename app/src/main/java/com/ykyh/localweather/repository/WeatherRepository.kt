package com.ykyh.localweather.repository

import com.ykyh.localweather.data.LocationResult
import com.ykyh.localweather.data.WeatherResult
import com.ykyh.localweather.network.RetrofitCreator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface WeatherRepository {

    fun getLocationSearch(query: String): Single<ArrayList<LocationResult>>
    fun getLocationWeather(woeid: Int): Single<WeatherResult>

}

class WeatherRepositoryImpl : WeatherRepository {
    override fun getLocationSearch(query: String): Single<ArrayList<LocationResult>> =
        RetrofitCreator.weatherRetrofit().getLocationSearch(query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getLocationWeather(woeid: Int): Single<WeatherResult> =
        RetrofitCreator.weatherRetrofit().getLocationWeather(woeid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
}