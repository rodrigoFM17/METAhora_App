package com.example.practica1.home.data.model

import com.google.gson.annotations.SerializedName

data class GoalDTO (
    val id: String,
    @SerializedName("user_id") val userId: String,
    val title: String,
    val description: String,
    val points: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("deleted_at") val deletedAt: String?,
    var text: String,
)