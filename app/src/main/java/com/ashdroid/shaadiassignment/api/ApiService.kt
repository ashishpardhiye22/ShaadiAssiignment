package com.ashdroid.shaadiassignment.api

import com.ashdroid.shaadiassignment.models.MatchProfileApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getProfiles(
        @Query("results") count: String = "10",
        @Query("page") page: String = "1"
    ): MatchProfileApiResponse

}