package com.gracie.billz.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("phone_number") var phoneNumber : String,
    @SerializedName("user_id")var userId : String,
    var email : String
)
