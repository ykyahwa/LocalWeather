package com.ykyh.localweather.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ykyh.localweather.R

class MainWeatherView @JvmOverloads constructor(context: Context,
                                                 attrs: AttributeSet? = null,
                                                 defStyleAttr: Int = 0): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.view_main_weather, this)
    }
}