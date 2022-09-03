package com.ashdroid.shaadiassignment

import android.app.Application
import androidx.room.Room
import com.ashdroid.shaadiassignment.api.ApiClient
import com.ashdroid.shaadiassignment.api.ApiService
import com.ashdroid.shaadiassignment.db.AppDatabase

class BaseApp : Application() {
    private lateinit var apiClient: ApiClient
    val apiService: ApiService by lazy {
        apiClient.getApiService()
    }

    val appDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "shaadi_db"
        ).build()
    }
    override fun onCreate() {
        super.onCreate()
        apiClient = ApiClient()
    }
}