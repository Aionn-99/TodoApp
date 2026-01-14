package com.example.todoapp.data.remote

import com.example.todoapp.data.Todo
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @FormUrlEncoded
    @POST("add_todo.php")
    suspend fun addTodo(
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("deadline") deadline: String,
        @Field("priority") priority: Int,
        @Field("category") category: String
    ): Response<ApiResponse>

    @FormUrlEncoded
    @POST("update_todo.php")
    suspend fun updateTodo(
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("deadline") deadline: String,
        @Field("priority") priority: Int,
        @Field("category") category: String,
        @Field("is_done") isDone: Int // 🔥 WAJIB
    ): Response<ApiResponse>

    @FormUrlEncoded
    @POST("delete_todo.php")
    suspend fun deleteTodo(
        @Field("id") id: Int
    ): Response<ApiResponse>
}