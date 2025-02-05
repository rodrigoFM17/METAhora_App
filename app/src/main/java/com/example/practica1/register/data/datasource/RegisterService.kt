package com.example.practica1.register.data.datasource

import com.example.practica1.register.data.model.RegisterDTO
import com.example.practica1.register.data.model.RegisterUserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("users/")
    suspend fun registerUser(@Body request: RegisterUserRequest) : Response<RegisterDTO>
}