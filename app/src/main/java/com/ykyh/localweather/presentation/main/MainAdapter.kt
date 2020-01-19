package com.ykyh.localweather.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ykyh.localweather.R
import com.ykyh.localweather.data.MAIN_VIEW_TYPE_HEADER
import com.ykyh.localweather.data.MainItem
import com.ykyh.localweather.presentation.base.BaseRecyclerViewAdapter
import com.ykyh.localweather.presentation.base.BaseViewHolder
import com.ykyh.localweather.presentation.base.RecyclerItem
import kotlinx.android.synthetic.main.item_main_weather.view.*
import javax.inject.Inject


class MainAdapter @Inject constructor(): BaseRecyclerViewAdapter<MainItem>() {

    override fun onCreateNewHolder(parent: ViewGroup, type: Int) =
        when(type) {
            MAIN_VIEW_TYPE_HEADER -> MainHeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_title, parent, false))
            else -> WeatherHeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_weather, parent, false))
        }

    inner class MainHeaderHolder(view: View): BaseViewHolder(view) {
        override fun onBindView(item: RecyclerItem?, position: Int) {}
    }

    inner class WeatherHeaderHolder(view: View): BaseViewHolder(view) {

        private val tvLocation = itemView.tvLocation
        private val weatherToday = itemView.weatherToday
        private val weatherTomorrow = itemView.weatherTomorrow

        override fun onBindView(item: RecyclerItem?, position: Int) {
            if (item is MainItem) {
                item.weatherItem?.let {weatherItem ->
                    tvLocation.text = weatherItem.local
                    weatherToday.setWeather(weatherItem.todayWeather)
                    weatherTomorrow.setWeather(weatherItem.tomorrowWeather)
                }
            }
        }

    }

}