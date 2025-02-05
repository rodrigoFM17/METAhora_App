package com.example.practica1.model


data class APIResponse<DataType> (
    val success: Boolean,
    val data: List<DataType>,
    val message: String
)

