package com.ykyh.localweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ykyh.localweather.R
import com.ykyh.localweather.repository.WeatherRepositoryImpl

class MainActivity : AppCompatActivity(), MainContract.MainView {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(WeatherRepositoryImpl())
        presenter.takeView(this)
    }
}
