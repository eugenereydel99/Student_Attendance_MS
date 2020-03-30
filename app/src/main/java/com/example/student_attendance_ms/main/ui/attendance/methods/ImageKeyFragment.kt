package com.example.student_attendance_ms.main.ui.attendance.methods

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.student_attendance_ms.R

/**
 * A simple [Fragment] subclass.
 */
class ImageKeyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return TextView(activity).apply {
            setText("Графический ключ")
        }
    }

}
