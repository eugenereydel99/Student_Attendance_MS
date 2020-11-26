package com.example.student_attendance_ms.main.schedule.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.viewModels
import com.example.student_attendance_ms.databinding.FragmentScheduleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var viewAdapter: EventAdapter

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val binding = FragmentScheduleBinding.inflate(inflater)
        binding.lifecycleOwner = this

        // инициализируем адаптер
        viewAdapter = EventAdapter(this)
        binding.eventsRecyclerView.adapter = viewAdapter

        calendarView = binding.calendarView

        viewModel.events.observe(viewLifecycleOwner) {
            viewAdapter.submitList(it)
        }

        // отображение событий в календаре при выборе определенной даты
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            viewModel.displayEventsByDate("${month + 1}/$dayOfMonth/$year")
        }

        return binding.root
    }

}
