package com.example.practica1.addGoal.data.repository

import com.example.practica1.addGoal.data.model.AddGoalRequest
import com.example.practica1.core.network.RetrofitHelper
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.model.APIResponse

class AddGoalRepository {

    private val addGoalService = RetrofitHelper.addGoalService

    suspend fun addGoal (request : AddGoalRequest): Result<APIResponse<GoalDTO>> {
        return try {
            val response = addGoalService.addGoal(request)

            if(response.isSuccessful){
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch(e: Exception) {
            Result.failure(e)
        }


    }
}