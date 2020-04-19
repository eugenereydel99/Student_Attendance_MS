package com.example.student_attendance_ms.main.schedule.subscription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R

/**
 * A simple [Fragment] subclass.
 */
class AttendanceEntryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: AttendanceEntryAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val userEntries = listOf(
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Рейдель Евгений Викторович"),
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Губайдуллин Андрей Евгеньевич"),
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Денисов Никита Михайлович"),
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Фокина Яна Игоревна"),
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Гордиенко Марк Александрович"),
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Ковалева Анастасия Павловна"),
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Половников Артем Евгеньевич"),
            UserEntry(R.drawable.ic_account_circle_black_24dp, "Иконников Сергей Сергеевич")
    )

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_attendace_entry, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = AttendanceEntryAdapter(userEntries)
        recyclerView = view.findViewById(R.id.users_recycler_view)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


}
