package com.ykyh.localweather.repository

import com.ykyh.localweather.data.LocationResult
import com.ykyh.localweather.data.WeatherResult
import com.ykyh.localweather.network.WeatherApi
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface WeatherRepository {
    fun getWeather(query: String): Observable<WeatherResult>
}

class WeatherRepositoryImpl @Inject constructor(val weatherApi: WeatherApi) : WeatherRepository {
    private fun getLocationSearch(query: String): Single<ArrayList<LocationResult>> =
        weatherApi.getLocationSearch(query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getLocationWeather(woeid: Int): Single<WeatherResult> =
        weatherApi.getLocationWeather(woeid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getWeather(query: String): Observable<WeatherResult> =
        getLocationSearch(query)
            .filter { locationList : ArrayList<LocationResult> -> locationList.isNotEmpty() }
            .flatMapObservable { list -> Observable.fromIterable(list) }
            .filter { location -> location.woeid != null }
            .flatMapSingle { location ->  getLocationWeather(location.woeid!!) }


}