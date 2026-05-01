package com.example.mvvmapp.data.repository

import com.example.mvvmapp.data.remote.network.RetrofitClient

class TodoRepository {
    suspend fun getTodos() = RetrofitClient.api.getTodos();
}