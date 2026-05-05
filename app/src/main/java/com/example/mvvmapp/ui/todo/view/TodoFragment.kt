package com.example.mvvmapp.ui.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.runtime.LaunchedEffect
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapp.databinding.FragmentTodoBinding
import com.example.customdialog.ui.common.dialog.*
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
        
        // Add dialog overlay
        val dialogOverlay = ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val dialogManager = rememberDialogManager()
                
                CustomDialog(
                    isVisible = dialogManager.dialogState.value.isVisible,
                    onDismiss = { dialogManager.hideDialog() },
                    title = dialogManager.dialogState.value.title,
                    message = dialogManager.dialogState.value.message,
                    positiveButtonText = dialogManager.dialogState.value.positiveButtonText,
                    negativeButtonText = dialogManager.dialogState.value.negativeButtonText,
                    onPositiveClick = dialogManager.dialogState.value.onPositiveClick,
                    onNegativeClick = dialogManager.dialogState.value.onNegativeClick,
                    showIcon = dialogManager.dialogState.value.showIcon,
                    iconText = dialogManager.dialogState.value.iconText
                )
            }
        }
        
        // Add dialog overlay to the root layout
        (binding.root as ViewGroup).addView(dialogOverlay)

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
        
        // Show welcome dialog when fragment is created
        showWelcomeDialog()
    }
    
    private fun showWelcomeDialog() {
        val dialogOverlay = (binding.root as ViewGroup).getChildAt(1) as ComposeView
        dialogOverlay.setContent {
            val dialogManager = rememberDialogManager()
            
            // Show welcome dialog after a short delay
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(500)
                dialogManager.showWelcomeDialog(
                    screenName = "Todos",
                    description = "Manage your task list here. View your todos fetched from the API and stay organized with your daily tasks."
                )
            }
            
            CustomDialog(
                isVisible = dialogManager.dialogState.value.isVisible,
                onDismiss = { dialogManager.hideDialog() },
                title = dialogManager.dialogState.value.title,
                message = dialogManager.dialogState.value.message,
                positiveButtonText = dialogManager.dialogState.value.positiveButtonText,
                negativeButtonText = dialogManager.dialogState.value.negativeButtonText,
                onPositiveClick = dialogManager.dialogState.value.onPositiveClick,
                onNegativeClick = dialogManager.dialogState.value.onNegativeClick,
                showIcon = dialogManager.dialogState.value.showIcon,
                iconText = dialogManager.dialogState.value.iconText
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}