package com.example.practica1.login.data.datasource

import com.example.practica1.login.data.model.LoginUserRequest
import com.example.practica1.login.data.model.UserDTO
import com.example.practica1.model.APIResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @POST("users/auth")
    suspend fun loginUser(@Body request : LoginUserRequest): Response<APIResponse<UserDTO>>
}