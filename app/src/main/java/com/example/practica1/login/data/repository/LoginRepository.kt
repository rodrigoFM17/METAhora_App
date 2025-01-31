package com.example.practica1.login.data.repository

import com.example.practica1.core.network.RetrofitHelper
import com.example.practica1.login.data.model.LoginUserRequest
import com.example.practica1.login.data.model.UserDTO

class LoginRepository {
    private val loginService = RetrofitHelper.loginService

    suspend fun login (request : LoginUserRequest): Result<UserDTO> {
        return try {
            val response = loginService.loginUser(request)
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