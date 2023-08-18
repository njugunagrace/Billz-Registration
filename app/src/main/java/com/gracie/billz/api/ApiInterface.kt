package com.gracie.billz.api

import com.gracie.billz.model.LoginRequest
import com.gracie.billz.model.LoginResponse
import com.gracie.billz.model.RegisterRequest
import com.gracie.billz.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/users/login")
    suspend fun loginUsers(@Body loginRequest: LoginRequest):Response<LoginResponse>

}