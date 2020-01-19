package com.ykyh.localweather.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.ykyh.localweather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class RetrofitCreator {
    companion object {
        const val API_BASE_URL = "https://www.metaweather.com/"

        fun createOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()

            val interceptor = HttpLoggingInterceptor(ApiLogger()) // Json PrettyPrinting
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            builder.addNetworkInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

            return builder.build()
        }
    }

    class ApiLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            val logName = "ApiLogger"
            if (message.startsWith("{") || message.startsWith("[")) {
                try {
                    val prettyPrintJson = GsonBuilder().setPrettyPrinting()
                        .create().toJson(JsonParser().parse(message))
                    Log.d(logName, prettyPrintJson)
                } catch (m: JsonSyntaxException) {
                    Log.d(logName, message)
                }
            } else {
                Log.d(logName, message)
                return
            }
        }
    }
}