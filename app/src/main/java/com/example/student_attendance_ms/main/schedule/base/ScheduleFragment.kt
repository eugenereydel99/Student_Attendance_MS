package com.example.student_attendance_ms.main.schedule.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R

/**
 * A simple [Fragment] subclass.
 */

private val events = listOf(
        Event("10:40\n12:15", "Прикладная криптография", "Лекция", "ТУСУР", "Якимук А.И"),
        Event("13:15\n14:50", "ММТСиС", "Практика", "ТУСУР", "Мастеров И.В."),
        Event("16:45\n18:20", "ГПО", "Практика", "ТУСУР", "Харченко С.С.")
)

class ScheduleFragment : Fragment(), EventCardAdapter.OnEventClickListener{

    private lateinit var calendarView: CalendarView
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: EventCardAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = EventCardAdapter(events, this)

        recyclerView = view.findViewById(R.id.events_recycler_view)

        return view
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

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


    }

    // переход во фрагмент на запись КП по нажатию элемента из списка событий
    override fun onEventClick(view: View, position: Int) {
        view.findNavController().navigate(R.id.action_scheduleFragment_to_attendanceEntryFragment, null)
    }

}
