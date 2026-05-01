package com.example.mvvmapp.ui.todo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.data.remote.model.TodoResponse
import com.example.mvvmapp.databinding.ItemStudentBinding
import com.example.mvvmapp.databinding.ItemTodoBinding

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var list = listOf<TodoResponse>()

    fun submitList(newList: List<TodoResponse>){
        Log.d("TodoAdapter", "submitList called with ${newList.size} items")
        list = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemTodoBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = list[position]
        Log.d("TodoAdapter", "Binding item at position $position: ${todo.title}")
        holder.binding.todo = todo
    }

    override fun getItemCount(): Int {
        Log.d("TodoAdapter", "getItemCount: ${list.size}")
        return list.size
    }
}