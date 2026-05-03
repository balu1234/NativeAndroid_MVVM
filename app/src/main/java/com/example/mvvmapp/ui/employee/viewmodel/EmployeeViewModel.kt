package com.example.mvvmapp.ui.employee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.local.entity.Employee
import com.example.mvvmapp.data.repository.EmployeeRepository
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repository: EmployeeRepository) : ViewModel() {

    val allEmployees: LiveData<List<Employee>> = repository.getAllEmployees()
    val employeeCount: LiveData<Int> = repository.getEmployeeCount()

    fun getEmployeesByDepartment(department: String): LiveData<List<Employee>> = 
        repository.getEmployeesByDepartment(department)

    fun insertEmployee(employee: Employee) = viewModelScope.launch {
        repository.insertEmployee(employee)
    }

    fun insertEmployees(employees: List<Employee>) = viewModelScope.launch {
        repository.insertEmployees(employees)
    }

    fun deleteEmployee(employee: Employee) = viewModelScope.launch {
        repository.deleteEmployee(employee)
    }

    fun deleteEmployeeById(id: Int) = viewModelScope.launch {
        repository.deleteEmployeeById(id)
    }

    fun deleteAllEmployees() = viewModelScope.launch {
        repository.deleteAllEmployees()
    }

    // Factory for creating ViewModel with repository
    class EmployeeViewModelFactory(private val repository: EmployeeRepository) : androidx.lifecycle.ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EmployeeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
