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

val weatherIconMap = hashMapOf(
    "sn" to "https://www.metaweather.com/static/img/weather/png/64/sn.png",
    "sl" to "https://www.metaweather.com/static/img/weather/png/64/sl.png",
    "h" to "https://www.metaweather.com/static/img/weather/png/64/h.png",
    "t" to "https://www.metaweather.com/static/img/weather/png/64/t.png",
    "hr" to "https://www.metaweather.com/static/img/weather/png/64/hr.png",
    "lr" to "https://www.metaweather.com/static/img/weather/png/64/lr.png",
    "s" to "https://www.metaweather.com/static/img/weather/png/64/s.png",
    "hc" to "https://www.metaweather.com/static/img/weather/png/64/hc.png",
    "lc" to "https://www.metaweather.com/static/img/weather/png/64/lc.png",
    "c" to "https://www.metaweather.com/static/img/weather/png/64/c.png")