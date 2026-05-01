package com.example.mvvmapp.ui.student.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.local.db.AppDatabase
import com.example.mvvmapp.data.local.entity.Student
import com.example.mvvmapp.data.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) :AndroidViewModel(application) {

    private val repository: StudentRepository
    val students : LiveData<List<Student>>

    init {
        val dao = AppDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(dao)
        students = repository.allStudents
    }

    fun insertStudent(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
}