package com.example.mvvmapp.data.repository

import com.example.mvvmapp.data.remote.api.EmployeeApiService
import com.example.mvvmapp.data.remote.model.EmployeeApiResponse
import com.example.mvvmapp.data.remote.model.EmployeeResponse
import com.example.mvvmapp.data.remote.network.RetrofitClient

class EmployeeApiRepository {
    
    suspend fun getEmployees(): List<EmployeeResponse> {
        return try {
            val response = RetrofitClient.employeeApi.getEmployees()
            if (response.status == "success") {
                response.data
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getEmployeeById(id: Int): EmployeeResponse? {
        return try {
            val response = RetrofitClient.employeeApi.getEmployeeById(id)
            if (response.status == "success" && response.data.isNotEmpty()) {
                response.data.first()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getEmployeesByDepartment(department: String): List<EmployeeResponse> {
        return try {
            val response = RetrofitClient.employeeApi.getEmployeesByDepartment(department)
            if (response.status == "success") {
                response.data
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
