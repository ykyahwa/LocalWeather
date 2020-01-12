package com.ykyh.localweather.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.ykyh.localweather.R
import com.ykyh.localweather.data.Weather
import kotlinx.android.synthetic.main.view_main_weather.view.*

class MainWeatherView @JvmOverloads constructor(context: Context,
                                                 attrs: AttributeSet? = null,
                                                 defStyleAttr: Int = 0): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.view_main_weather, this)
    }

    fun setWeather(weather: Weather?) {
        weather?.let { nonNullWeather ->
            Glide.with(this)
                .load(weatherIconMap[nonNullWeather.weatherStateAbbr])
                .into(ivWeather)
            tvWeather.text = nonNullWeather.weatherStateName
            tvTemp.text = context.getString(R.string.temp, nonNullWeather.theTemp?.toInt())
            tvHumidity.text = context.getString(R.string.humidity,  nonNullWeather.humidity?.toInt())
        }
    }
}