package com.ykyh.localweather.presentation.main

import com.ykyh.localweather.data.MainItem

interface MainContract{
    interface MainView {
        fun addWeatherItem(item: MainItem)
        fun addTitleAndNotify(item: MainItem)
        fun progressVisible(isVisible: Boolean)
        fun clearList()
        fun showError()
    }

    interface MainPresenter {
        fun takeView(view: MainView)
        fun dropView()

        fun requestWeather(query: String)
        fun refresh()

    }
}