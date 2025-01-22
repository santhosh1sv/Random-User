package com.nisum.test.randomuser.business_logic.repository

import com.nisum.test.randomuser.business_logic.ApiService
import com.nisum.test.randomuser.business_logic.data.RandomUsersModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RandomUsersApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getRandomUsers(page: Int, results: Int) = apiService.getRandomUsers(page, results)
}


