package com.example.student_attendance_ms.main.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import com.example.student_attendance_ms.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {

    private lateinit var calendarView: CalendarView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calendarView = view.findViewById(R.id.schedule)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Toast.makeText(
                    context,
                    "$dayOfMonth/$month/$year",
                    Toast.LENGTH_LONG
            ).show()
        }

    }

}
