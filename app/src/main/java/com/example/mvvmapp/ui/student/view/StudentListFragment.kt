package com.example.mvvmapp.ui.student.view

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
import com.example.mvvmapp.databinding.FragmentStudentListBinding
import com.example.customdialog.ui.common.dialog.*
import com.example.mvvmapp.ui.student.adapter.StudentAdapter
import com.example.mvvmapp.ui.student.viewmodel.StudentViewModel

class StudentListFragment : Fragment() {

    private lateinit var binding: FragmentStudentListBinding
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        
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
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        adapter = StudentAdapter {
            viewModel.delete(it)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.students.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        
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
                    screenName = "Students",
                    description = "Manage your student records here. You can add new students, view existing ones, and delete students as needed."
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
}