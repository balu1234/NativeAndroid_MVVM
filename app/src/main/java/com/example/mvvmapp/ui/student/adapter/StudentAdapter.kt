package com.example.mvvmapp.ui.student.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import com.example.mvvmapp.data.local.entity.Student
import com.example.mvvmapp.databinding.ItemStudentBinding

class StudentAdapter(
    private val onDelete: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private var list = listOf<Student>()

    fun submitList(newList: List<Student>){
        list = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder (val binding: ItemStudentBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = list[position]
        holder.binding.student = student

        holder.binding.btnDelete.setOnClickListener {
            onDelete(student)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}