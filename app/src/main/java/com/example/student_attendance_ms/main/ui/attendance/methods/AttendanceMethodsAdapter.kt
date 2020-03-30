package com.example.student_attendance_ms.main.ui.attendance.methods

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class AttendanceMethodsAdapter(
        fragment: Fragment
): FragmentStateAdapter(fragment){

    private val methodsTabs: List<Fragment> = arrayListOf(
            ScannerFragment(),
            ImageKeyFragment(),
            TestsFragment()
    )

    override fun getItemCount(): Int {
        return methodsTabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return methodsTabs[position]
    }

}