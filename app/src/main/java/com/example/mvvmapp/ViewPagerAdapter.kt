package com.example.mvvmapp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvvmapp.ui.employee.EmployeeApiFragment
import com.example.mvvmapp.ui.employee.EmployeeRoomFragment
import com.example.mvvmapp.ui.student.view.StudentListFragment
import com.example.mvvmapp.ui.todo.view.TodoFragment


class  ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StudentListFragment()
            1 -> TodoFragment()
            2 -> EmployeeRoomFragment()
            3 -> EmployeeApiFragment()
            else -> StudentListFragment()
        }
    }

}