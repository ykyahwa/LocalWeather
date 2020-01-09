package com.ykyh.localweather.data

import com.google.gson.annotations.SerializedName

data class LocationResult(
    @SerializedName("title") val title: String?,
    @SerializedName("location_type") val locationType: String?,
    @SerializedName("latt_long") val lattLong: String?,
    @SerializedName("woeid") val woeid: Int?,
    @SerializedName("distance") val distance: Int?)

data class WeatherResult(
    @SerializedName("consolidated_weather") val consolidatedWeather: ArrayList<Weather>?)

data class Weather(
    @SerializedName("weather_state_name") val weatherStateName: String?,
    @SerializedName("weather_state_abbr") val weatherStateAbbr: String?,
    @SerializedName("the_temp") val theTemp: Long?,
    @SerializedName("humidity") val humidity: Long?)