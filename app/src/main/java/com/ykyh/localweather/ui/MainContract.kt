package com.ykyh.localweather.ui

interface MainContract{
    interface MainView {

    }

    interface MainPresenter {
        fun takeView(view: MainView)
        fun dropView()

        fun requestWeather(query: String)

    }
}