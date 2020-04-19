package com.example.student_attendance_ms.main.methods

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.student_attendance_ms.main.methods.image_key.ImageKeyFragment
import com.example.student_attendance_ms.main.methods.scanner.ScannerFragment
import com.example.student_attendance_ms.main.methods.testing.TestingFragment

class AttendanceMethodsAdapter(
        fragment: Fragment
): FragmentStateAdapter(fragment){

    private val methodsTabs: List<Fragment> = arrayListOf(
            ImageKeyFragment(),
            ScannerFragment(),
            TestingFragment()
    )

    override fun getItemCount(): Int {
        return methodsTabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return methodsTabs[position]
    }

}