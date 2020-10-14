package com.example.student_attendance_ms.main.attendance

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.student_attendance_ms.main.attendance.image_key.ImageKeyFragment
import com.example.student_attendance_ms.main.attendance.scanner.ScannerFragment
import com.example.student_attendance_ms.main.attendance.testing.TestingFragment

class AttendanceMethodsAdapter (
        fragment: Fragment
): FragmentStateAdapter(fragment) {

    private val methodsTabs = arrayListOf<Fragment>(
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