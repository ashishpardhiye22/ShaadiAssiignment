package com.ashdroid.shaadiassignment.repositories

import com.ashdroid.shaadiassignment.BaseApp

class ApiRepository(private val app: BaseApp) {
    suspend fun getProfiles(page: String) = app.apiService.getProfiles(page = page)
}
