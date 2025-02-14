package com.example.practica1.home.domain

import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.home.data.repository.HomeRepository
import com.example.practica1.model.APIResponse

class CompleteGoalUseCase {

    private val repository = HomeRepository()

    suspend operator fun invoke( goalId: String ): Result<APIResponse<GoalDTO>> {
        val result = repository.completeGoal(goalId)
        return result
    }

}