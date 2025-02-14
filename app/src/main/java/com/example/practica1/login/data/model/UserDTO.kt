package com.example.practica1.login.data.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: String,
    val nickname: String,
    val email: String,
    val password: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("deleted_at") val deletedAt: String?
)