package com.example.student_attendance_ms.main.ui.attendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.student_attendance_ms.R

class AttendanceFragment : Fragment() {

    companion object {
        fun newInstance() = AttendanceFragment()
    }

    private lateinit var viewModel: AttendanceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.attendance_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AttendanceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
