package com.example.mvvmapp.data.remote.network

import com.example.mvvmapp.data.remote.api.ApiService
import com.example.mvvmapp.data.remote.api.EmployeeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val employeeApi: EmployeeApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeApiService::class.java)
    }
}