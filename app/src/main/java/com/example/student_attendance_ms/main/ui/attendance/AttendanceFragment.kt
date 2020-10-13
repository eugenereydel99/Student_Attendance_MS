package com.example.student_attendance_ms.main.ui.attendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2

import com.example.student_attendance_ms.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AttendanceFragment : Fragment() {
    private lateinit var attendanceMethodsAdapter: AttendanceMethodsAdapter
    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pager_attendance_methods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        attendanceMethodsAdapter = AttendanceMethodsAdapter(this)
        viewPager2 = view.findViewById(R.id.ams_pager)
        viewPager2.adapter = attendanceMethodsAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.attendance_methods)
        TabLayoutMediator(tabLayout, viewPager2){ tab, position ->
            when (position){
                0 -> tab.text = resources.getString(R.string.title_image_key)
                1 -> tab.text = resources.getString(R.string.title_qr_scanner)
                2 -> tab.text = resources.getString(R.string.title_solve_tests)
            }
        }.attach()
    }

}
