package com.example.mvvmapp.ui.employee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.remote.model.EmployeeResponse
import com.example.mvvmapp.data.repository.EmployeeApiRepository
import kotlinx.coroutines.launch

class EmployeeApiViewModel : ViewModel() {

    private val repository = EmployeeApiRepository()

    private val _employees = MutableLiveData<List<EmployeeResponse>>()
    val employees: LiveData<List<EmployeeResponse>> = _employees

    private val _employee = MutableLiveData<EmployeeResponse?>()
    val employee: LiveData<EmployeeResponse?> = _employee

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchEmployees() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getEmployees()
                _employees.value = result
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to fetch employees: ${e.message}"
                _employees.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchEmployeeById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getEmployeeById(id)
                _employee.value = result
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to fetch employee: ${e.message}"
                _employee.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchEmployeesByDepartment(department: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getEmployeesByDepartment(department)
                _employees.value = result
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to fetch employees by department: ${e.message}"
                _employees.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
