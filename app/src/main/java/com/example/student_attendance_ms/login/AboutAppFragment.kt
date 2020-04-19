package com.example.student_attendance_ms.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.student_attendance_ms.R

/**
 * A simple [Fragment] subclass.
 */
class AboutAppFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment_about_app, container, false)
    }

}
