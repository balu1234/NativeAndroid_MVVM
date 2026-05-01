package com.example.mvvmapp.ui.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapp.databinding.FragmentTodoBinding
import com.example.mvvmapp.ui.student.viewmodel.StudentViewModel
import com.example.mvvmapp.ui.todo.adapter.TodoAdapter
import com.example.mvvmapp.ui.todo.viewmodel.TodoViewModel

class TodoFragment : Fragment() {

    private  var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTodoBinding.inflate(inflater,container,false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        adapter = TodoAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        //observe
        viewModel.todos.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        // call api
        viewModel.fetchTodos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}