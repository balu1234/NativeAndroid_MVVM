package com.example.mvvmapp.data.remote.api

import com.example.mvvmapp.data.remote.model.EmployeeApiResponse
import com.example.mvvmapp.data.remote.model.EmployeeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeApiService {
    @GET("api/v1/employees")
    suspend fun getEmployees(): EmployeeApiResponse

    @GET("api/v1/employee/{id}")
    suspend fun getEmployeeById(@Path("id") id: Int): EmployeeApiResponse

    @GET("api/v1/employees")
    suspend fun getEmployeesByDepartment(@Path("department") department: String): EmployeeApiResponse
}
