package com.example.practica1.core.network

import com.example.practica1.login.data.datasource.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {
    private const val BASE_URL = "https://"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }


}