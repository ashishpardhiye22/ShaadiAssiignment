package com.ashdroid.shaadiassignment.api

import com.ashdroid.shaadiassignment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Atto Infotech on 8/24/2021
 */
class ApiClient {
    fun getApiService(): ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .baseUrl("https://randomuser.me/")
        .build()
        .create(ApiService::class.java)

    private fun getOkHttpClient(): OkHttpClient {
        try {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(logging)
            }
            builder.readTimeout(1, TimeUnit.MINUTES)
            builder.writeTimeout(1, TimeUnit.MINUTES)
            builder.connectTimeout(1, TimeUnit.MINUTES)
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}