package com.gracie.billz.model


data class LoginResponse(
    var message:String,
    var access_token:String,
    var user_id:String
)
