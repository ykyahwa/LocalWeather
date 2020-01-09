package com.ykyh.localweather.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.ykyh.localweather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitCreator {
    companion object {
        private const val API_BASE_URL = "https://www.metaweather.com/"

        fun weatherRetrofit(): WeatherApi =
            Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build()
                .create(WeatherApi::class.java)

        private fun createOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()

            val interceptor = HttpLoggingInterceptor(ApiLogger()) // Json PrettyPrinting
//            val interceptor = HttpLoggingInterceptor()
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
//                .addInterceptor { chain ->
//                    val request = chain.request().newBuilder()
//                        .addHeader("Device-Type", "1")
//                        .addHeader("App-Version", BuildConfig.VERSION_NAME)
//                        .addHeader("Revision", "2019")
//                        .addHeader("api-key", GlobalPreference.api_key)  // 비로그인 : not_login_user_key ,로그인 : 서버에서 받은 api_key
//                        .addHeader("Content-Type", "application/json")
//                        .build()
//                    chain.proceed(request)
//                }

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