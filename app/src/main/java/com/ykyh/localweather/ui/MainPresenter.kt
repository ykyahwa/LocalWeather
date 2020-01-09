package com.ykyh.localweather.ui

import android.util.Log
import com.ykyh.localweather.data.LocationResult
import com.ykyh.localweather.repository.WeatherRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

private const val targetLocation = "se"

class MainPresenter(private val weatherRepository: WeatherRepository): MainContract.MainPresenter {

    private var view: MainContract.MainView? = null
    private var compositeDisposable: CompositeDisposable? = null

    override fun takeView(view: MainContract.MainView) {
        this.view = view
        compositeDisposable = CompositeDisposable()

        requestWeather(targetLocation)
    }

    override fun dropView() {
        view = null
        compositeDisposable?.dispose()
        compositeDisposable = null
    }

    override fun requestWeather(query: String) {
        weatherRepository.getLocationSearch(query)
            .filter { locationList : ArrayList<LocationResult> -> locationList.isNotEmpty() }
            .flatMapObservable { list -> Observable.fromIterable(list) }
            .filter { location -> location.woeid != null }
            .flatMapSingle { location ->  weatherRepository.getLocationWeather(location.woeid!!) }
            .subscribe({ result ->
                Log.d("TEST","result = $result")

            },{
                it.printStackTrace()
            }, {

            })
            .apply { compositeDisposable?.add(this) }

    }


}