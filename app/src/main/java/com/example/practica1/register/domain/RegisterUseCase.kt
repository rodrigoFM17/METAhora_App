package com.example.practica1.register.domain

import android.util.Log
import com.example.practica1.register.data.model.RegisterDTO
import com.example.practica1.register.data.model.RegisterUserRequest
import com.example.practica1.register.data.repository.RegisterRepository

class RegisterUseCase {
    private val repository = RegisterRepository()

    suspend operator fun invoke(user: RegisterUserRequest): Result<RegisterDTO> {
        Log.d("API", user.toString())
        val result = repository.register(user)
        return result
    }
}