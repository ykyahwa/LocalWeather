package com.ykyh.localweather.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ykyh.localweather.R
import com.ykyh.localweather.repository.WeatherRepositoryImpl
import com.ykyh.localweather.util.setDivider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

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
        rvList.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.left = 30
                outRect.right = 30
            }
        })
        rvList.setDivider(R.drawable.main_recyclerview_divider)

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.refresh()
        }
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

    override fun clearList() {
        adapter.clear()
        adapter.notifyDataSetChanged()
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }

}
