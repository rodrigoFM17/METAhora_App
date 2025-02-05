package com.example.practica1.home.data.repository

import com.example.practica1.core.network.RetrofitHelper
import com.example.practica1.home.data.model.GetAllGoalsRequest
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.model.APIResponse

class HomeRepository() {
    private val homeService = RetrofitHelper.homeService

    suspend fun getAllGoals(request: GetAllGoalsRequest): Result<APIResponse<GoalDTO>> {
        return try {
            val response = homeService.getAllGoals(request.userId)
            if(response.isSuccessful){
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}