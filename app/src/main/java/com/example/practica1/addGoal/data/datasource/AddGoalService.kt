package com.example.practica1.addGoal.data.datasource

import com.example.practica1.addGoal.data.model.AddGoalRequest
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.model.APIResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddGoalService {
    @POST("goals/")
    suspend fun addGoal(@Body request: AddGoalRequest): Response<APIResponse<GoalDTO>>
}