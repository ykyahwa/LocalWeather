package com.ykyh.localweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ykyh.localweather.R
import com.ykyh.localweather.repository.WeatherRepositoryImpl
import com.ykyh.localweather.util.setDivider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    lateinit var presenter: MainPresenter
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(WeatherRepositoryImpl())
        presenter.takeView(this)

        initView()
    }

    private fun initView() {
        adapter = MainAdapter()
        rvList.adapter = adapter
        rvList.setDivider(R.drawable.main_recyclerview_divider)
    }

    override fun addWeatherItem(item: MainItem) {
        adapter.addItem(item)
    }

    override fun addTitleAndNotify(item: MainItem) {
        adapter.addItem(0, item)
        adapter.notifyDataSetChanged()
    }

    override fun progressVisible(isVisible: Boolean) {
        pbProgress.isVisible = isVisible
    }

}
