package com.example.todoapp.data.remote

data class ApiResponse(
    val success: Boolean,
    val message: String,
    val data: Any? = null
)
