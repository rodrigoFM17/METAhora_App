package com.example.practica1.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: List<UserDTO>,
    @SerializedName("message") val message: String
)