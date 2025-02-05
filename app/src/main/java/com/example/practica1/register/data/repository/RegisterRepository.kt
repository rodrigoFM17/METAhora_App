package com.example.practica1.register.data.repository

import android.util.Log
import com.example.practica1.core.network.RetrofitHelper
import com.example.practica1.register.data.model.RegisterDTO
import com.example.practica1.register.data.model.RegisterUserRequest

class RegisterRepository() {
    private val registerService = RetrofitHelper.registerService

    suspend fun register (request : RegisterUserRequest): Result<RegisterDTO> {
        return try {
            val response = registerService.registerUser(request)
            Log.d("DEBUG", response.toString())
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