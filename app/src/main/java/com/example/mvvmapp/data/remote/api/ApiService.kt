package com.example.mvvmapp.data.remote.api

import com.example.mvvmapp.data.remote.model.TodoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("todos")
    suspend fun getTodos(): List<TodoResponse>
}