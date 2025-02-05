package com.example.practica1.home.domain

import com.example.practica1.home.data.model.GetAllGoalsRequest
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.home.data.repository.HomeRepository
import com.example.practica1.model.APIResponse

class GetAllGoalsUseCase {

    private val repository = HomeRepository()

    suspend operator fun invoke( request: GetAllGoalsRequest): Result<APIResponse<GoalDTO>> {
        val result = repository.getAllGoals(request)
        return result
    }
}