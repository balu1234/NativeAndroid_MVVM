package com.example.mvvmapp.ui.student.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.R
import com.example.mvvmapp.data.local.entity.Student
import com.example.mvvmapp.databinding.ActivityAddStudentBinding
import com.example.mvvmapp.ui.student.viewmodel.StudentViewModel


class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_add_student)
        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Student"

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnAdd.setOnClickListener {
            val student = Student(
                name = binding.etName.text.toString(),
                age = binding.etAge.text.toString().toInt(),
                course = binding.etCourse.text.toString()
            )

            viewModel.insertStudent(student)
            finish() // Go back to main activity after adding
        }

    }

}