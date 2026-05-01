package com.example.mvvmapp.data.repository

import com.example.mvvmapp.data.local.dao.StudentDao
import com.example.mvvmapp.data.local.entity.Student

class StudentRepository(private val dao:StudentDao) {

    val allStudents = dao.getAllStudents()

    suspend fun insert(student: Student) {
        dao.insertStudent(student)
    }

    suspend fun delete(student: Student) {
        dao.deleteStudent(student)
    }
}