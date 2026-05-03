package com.example.mvvmapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("employee_name")
    val employeeName: String,
    @SerializedName("employee_salary")
    val employeeSalary: String,
    @SerializedName("employee_age")
    val employeeAge: String,
    @SerializedName("profile_image")
    val profileImage: String
)

// Wrapper class for the API response
data class EmployeeApiResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<EmployeeResponse>,
    @SerializedName("message")
    val message: String
)
