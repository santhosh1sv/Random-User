package com.nisum.test.randomuser.business_logic

import com.nisum.test.randomuser.business_logic.data.RandomUsersModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
   suspend fun getRandomUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String = "abc"
    ): RandomUsersModel
}