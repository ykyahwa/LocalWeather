package com.ykyh.localweather.ui

interface MainContract{
    interface MainView {

        fun addWeatherItem(item: MainItem)
        fun addTitleAndNotify(item: MainItem)
        fun progressVisible(isVisible: Boolean)
    }

    interface MainPresenter {
        fun takeView(view: MainView)
        fun dropView()

        fun requestWeather(query: String)

    }
}