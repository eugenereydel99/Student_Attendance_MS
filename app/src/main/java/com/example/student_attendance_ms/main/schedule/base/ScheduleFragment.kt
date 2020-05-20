package com.example.student_attendance_ms.main.schedule.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentScheduleBinding

/**
 * A simple [Fragment] subclass.
 */

class ScheduleFragment : Fragment(){

    private lateinit var calendarView: CalendarView
    private lateinit var viewAdapter: EventAdapter

    private val viewModel: ScheduleViewModel by lazy {
        ViewModelProvider(this).get(ScheduleViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentScheduleBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // инициализируем адаптер
        viewAdapter = EventAdapter(EventAdapter.OnClickListener{
            view?.findNavController()?.navigate(R.id.action_scheduleFragment_to_attendanceEntryFragment, null)
        })
        binding.eventsRecyclerView.adapter = viewAdapter

        viewModel.events.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewAdapter.submitList(it)
            }
        })

//        viewAdapter = EventAdapter()
//        recyclerView = binding.eventsRecyclerView
//        recyclerView.apply {
//            setHasFixedSize(true)
//            this.adapter = viewAdapter
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        calendarView = view.findViewById(R.id.schedule_view)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            Toast.makeText(
                    context,
                    "$dayOfMonth/$month/$year",
                    Toast.LENGTH_LONG
            ).show()
        }

    }
}
