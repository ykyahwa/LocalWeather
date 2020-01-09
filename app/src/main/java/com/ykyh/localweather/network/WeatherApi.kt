package com.ykyh.localweather.network

import com.ykyh.localweather.data.LocationResult
import com.ykyh.localweather.data.WeatherResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("/api/location/search/")
    fun getLocationSearch(@Query("query") query: String): Single<ArrayList<LocationResult>>

    @GET("/api/location/{woeid}/")
    fun getLocationWeather(@Path("woeid") woeid: Int): Single<WeatherResult>
}