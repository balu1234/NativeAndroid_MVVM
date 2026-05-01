package com.example.mvvmapp.ui.todo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.remote.model.TodoResponse
import com.example.mvvmapp.data.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repo = TodoRepository()

    val todos = MutableLiveData<List<TodoResponse>>()

    fun fetchTodos() {
        Log.d("TodoViewModel", "fetchTodos called")
        viewModelScope.launch {
           try {
               Log.d("TodoViewModel", "Making API call")
               val result = repo.getTodos()
               Log.d("TodoViewModel", "API call successful, got ${result.size} todos")
               todos.value = result
           } catch (e: Exception) {
               Log.e("TodoViewModel", "API call failed", e)
               e.printStackTrace()
           }
        }
    }
}