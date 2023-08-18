package com.gracie.billz.repository

import com.gracie.billz.ApiClient
import com.gracie.billz.api.ApiInterface
import com.gracie.billz.model.LoginRequest
import com.gracie.billz.model.LoginResponse
import com.gracie.billz.model.RegisterRequest
import com.gracie.billz.model.RegisterResponse
import com.gracie.billz.ui.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun register(registerRequest: RegisterRequest) : Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }
    suspend fun LoginActivity(loginRequest: LoginRequest):Response<LoginResponse>{
        return withContext(Dispatchers.IO){
            apiClient.loginUsers(loginRequest)
        }

    }
}