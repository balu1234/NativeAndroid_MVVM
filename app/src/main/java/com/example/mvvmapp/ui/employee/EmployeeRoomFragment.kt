package com.example.mvvmapp.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.mvvmapp.data.local.db.AppDatabase
import com.example.mvvmapp.ui.employee.compose.EmployeeRoomScreen

class EmployeeRoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val database = AppDatabase.getDatabase(requireContext())
                EmployeeRoomScreen(database = database)
            }
        }
    }
}
