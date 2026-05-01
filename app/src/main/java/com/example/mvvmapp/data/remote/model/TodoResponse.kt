package com.example.mvvmapp.data.remote.model

data class TodoResponse (
    val id: Int,
    val title: String,
    val completed: Boolean
)