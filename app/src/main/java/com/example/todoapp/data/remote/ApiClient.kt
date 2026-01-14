package com.example.todoapp.data.remote

import com.example.todoapp.data.Todo
import com.example.todoapp.data.TodoDeserializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://appocalypse.my.id/api_kel8_IFVIIA/"

    fun create(): ApiService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // Custom Gson untuk konversi is_done (integer 0/1) ke Boolean
        val gson = GsonBuilder()
            .registerTypeAdapter(Todo::class.java, TodoDeserializer())
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}