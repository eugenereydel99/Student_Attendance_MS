package com.example.student_attendance_ms.main.schedule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.student_attendance_ms.databinding.FragmentScheduleBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ScheduleFragment : Fragment() {

    private lateinit var viewAdapter: EventAdapter

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScheduleBinding.inflate(inflater)
        
        binding.lifecycleOwner = this

        // инициализируем адаптер
        viewAdapter = EventAdapter(this)
        binding.eventsRecyclerView.adapter = viewAdapter

        viewModel.events.observe(viewLifecycleOwner){
            viewAdapter.submitList(it)
        }

        // отображение событий в календаре при выборе определенной даты
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = if (dayOfMonth != 2) "$year/${month+1}/0$dayOfMonth" else "$year/${month+1}/$dayOfMonth"
            viewModel.displayEventsByDate(date = date)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
