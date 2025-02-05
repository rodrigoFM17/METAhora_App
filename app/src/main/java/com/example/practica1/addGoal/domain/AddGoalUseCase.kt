package com.example.practica1.addGoal.domain

import com.example.practica1.addGoal.data.model.AddGoalRequest
import com.example.practica1.addGoal.data.repository.AddGoalRepository
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.model.APIResponse

class AddGoalUseCase {

    private val addGoalRepository = AddGoalRepository()

    suspend operator fun invoke ( request: AddGoalRequest): Result<APIResponse<GoalDTO>> {
        val result = addGoalRepository.addGoal(request)
        return result
    }

}