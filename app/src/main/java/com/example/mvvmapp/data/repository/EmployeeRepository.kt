package com.example.mvvmapp.data.repository

import androidx.lifecycle.LiveData
import com.example.mvvmapp.data.local.dao.EmployeeDao
import com.example.mvvmapp.data.local.entity.Employee

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    fun getAllEmployees(): LiveData<List<Employee>> = employeeDao.getAllEmployees()

    fun getEmployeesByDepartment(department: String): LiveData<List<Employee>> = 
        employeeDao.getEmployeesByDepartment(department)

    suspend fun insertEmployee(employee: Employee) = employeeDao.insertEmployee(employee)

    suspend fun insertEmployees(employees: List<Employee>) = employeeDao.insertEmployees(employees)

    suspend fun getEmployeeById(id: Int): Employee? = employeeDao.getEmployeeById(id)

    suspend fun deleteEmployee(employee: Employee) = employeeDao.deleteEmployee(employee)

    suspend fun deleteEmployeeById(id: Int) = employeeDao.deleteEmployeeById(id)

    suspend fun deleteAllEmployees() = employeeDao.deleteAllEmployees()

    fun getEmployeeCount(): LiveData<Int> = employeeDao.getEmployeeCount()
}
