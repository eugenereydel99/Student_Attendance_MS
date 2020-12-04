package com.example.student_attendance_ms.main.schedule.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.student_attendance_ms.databinding.FragmentScheduleBinding
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {

    private lateinit var viewAdapter: EventAdapter

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScheduleBinding.inflate(inflater)
        
        binding.lifecycleOwner = this

        // инициализируем адаптер
        viewAdapter = EventAdapter(this)
        binding.eventsRecyclerView.adapter = viewAdapter

        attachObservers()

        return binding.root
    }

    private fun attachObservers(){

        // отображение событий в календаре при выборе определенной даты
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = if (dayOfMonth != 2) "$year/${month+1}/0$dayOfMonth" else "$year/${month+1}/$dayOfMonth"
            viewModel.displayEventsByDate(date = date)
        }

        viewModel.dataState.observe(viewLifecycleOwner){ dataState ->
            when (dataState){
                is DataState.Success<List<Event>> -> {
                    displayProgressBar(false)
                    submitEventList(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(activity, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                }
                DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        val progressBar = binding.progressBar
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun submitEventList(events: List<Event>){
        viewAdapter.submitList(events)
    }
}
