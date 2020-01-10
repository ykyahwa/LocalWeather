package com.ykyh.localweather.ui

import com.ykyh.localweather.data.Weather
import com.ykyh.localweather.ui.base.RecyclerItem

const val MAIN_VIEW_TYPE_HEADER = 0
const val MAIN_VIEW_TYPE_WEATHER = 1

data class MainItem(
    val titleItem: MainTitleItem? = null,
    val weatherItem: MainWeatherItem? = null
): RecyclerItem()

data class MainTitleItem(val localTitle: String,
                         val todayTitle: String,
                         val tomorrowTitle: String)

data class MainWeatherItem(val local: String?,
                           val todayWeather: Weather?,
                           val tomorrowWeather: Weather?)