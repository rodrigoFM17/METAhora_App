package com.example.practica1.home.data.datasource

import com.example.practica1.home.data.model.GetAllGoalsRequest
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.model.APIResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("users/{userId}/goals")
    suspend fun getAllGoals(@Path("userId") userId: String): Response<APIResponse<GoalDTO>>
}