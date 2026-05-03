package com.example.mvvmapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.mvvmapp.data.local.entity.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployees(employees: List<Employee>)

    @Query("SELECT * FROM employees ORDER BY name ASC")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Query("SELECT * FROM employees WHERE id = :id")
    suspend fun getEmployeeById(id: Int): Employee?

    @Query("SELECT * FROM employees WHERE department = :department ORDER BY name ASC")
    fun getEmployeesByDepartment(department: String): LiveData<List<Employee>>

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("DELETE FROM employees WHERE id = :id")
    suspend fun deleteEmployeeById(id: Int)

    @Query("DELETE FROM employees")
    suspend fun deleteAllEmployees()

    @Query("SELECT COUNT(*) FROM employees")
    fun getEmployeeCount(): LiveData<Int>
}
