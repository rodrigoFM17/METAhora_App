package com.example.practica1.login.domain

import com.example.practica1.login.data.model.LoginUserRequest
import com.example.practica1.login.data.model.UserDTO
import com.example.practica1.login.data.repository.LoginRepository

class LoginUserUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(user: LoginUserRequest): Result<UserDTO> {
        val result = repository.login(user)
        return result
    }
}