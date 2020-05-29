package com.example.student_attendance_ms.main.schedule.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentScheduleBinding
import org.json.JSONObject

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

        calendarView = binding.calendarView

        viewModel.events.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewAdapter.submitList(it)
            }
        })

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
//            val currentDate = JSONObject().put(
//                    "date","$month/$dayOfMonth/$year"
//            ).toString().replace("\\","")
            viewModel.displayEventsByDate("${month+1}/$dayOfMonth/$year")
//            Toast.makeText(context, "${month+1}/$dayOfMonth/$year", Toast.LENGTH_LONG).show()


//            viewModel.events.observe(viewLifecycleOwner, Observer {
//                it?.let {
//                    viewAdapter.submitList(it)
//                }
//            })

        }

        return binding.root
    }

    private fun getDate(viewModel: ScheduleViewModel){

    }
}
