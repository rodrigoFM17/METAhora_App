package com.example.practica1.addGoal.data.model

data class AddGoalRequest (
    val userId: String,
    val title: String,
    val description: String
)