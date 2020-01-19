package com.ykyh.localweather.presentation

import com.ykyh.localweather.data.*
import com.ykyh.localweather.repository.WeatherRepository
import io.reactivex.disposables.CompositeDisposable

internal const val targetLocation = "se"

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
        view?.progressVisible(true)

        weatherRepository.getWeather(query)
            .map { result -> makeMainData(result) }
            .subscribe({ result ->
                view?.addWeatherItem(result)
            },{
                it.printStackTrace()
                view?.progressVisible(false)
                view?.showError()
            }, {
                view?.addTitleAndNotify(makeTitleItem())
                view?.progressVisible(false)
            })
            .apply { compositeDisposable?.add(this) }

    }

    override fun refresh() {
        view?.clearList()
        requestWeather(targetLocation)
    }

    private fun makeTitleItem() =
        MainItem(
            titleItem = MainTitleItem(
                "Local",
                "Today",
                "tomorrow"
            )
        ).apply {
            viewType = MAIN_VIEW_TYPE_HEADER
        }

    private fun makeMainData(result: WeatherResult) =
        MainItem(
            weatherItem = MainWeatherItem(
                result.title,
                result.consolidatedWeather?.get(0),
                result.consolidatedWeather?.get(1)
            )
        ).apply {
            viewType = MAIN_VIEW_TYPE_WEATHER
        }


}