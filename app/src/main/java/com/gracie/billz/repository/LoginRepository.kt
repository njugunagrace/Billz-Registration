package com.gracie.billz.repository

import com.gracie.billz.ApiClient
import com.gracie.billz.api.ApiInterface
import com.gracie.billz.model.LoginRequest
import com.gracie.billz.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
//    val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun login(loginRequest: LoginRequest):Any{
        return withContext(Dispatchers.IO){
            val apiClient = ApiClient.buildClient(ApiInterface::class.java)
            apiClient.loginUsers(loginRequest)
        }
    }
}