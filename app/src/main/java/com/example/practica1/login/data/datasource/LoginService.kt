package com.example.practica1.login.data.datasource

import com.example.practica1.login.data.model.LoginUserRequest
import com.example.practica1.login.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("auth")
    suspend fun loginUser(@Body request : LoginUserRequest): Response<UserDTO>
}