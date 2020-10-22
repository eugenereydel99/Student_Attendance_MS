package com.example.student_attendance_ms.main.schedule.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.network.model.EventMember

/**
 * A simple [Fragment] subclass.
 */
class EventDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: EventDetailAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val userEntries = listOf(
            EventMember(R.drawable.ic_account_circle_black_24dp, "Рейдель Евгений Викторович"),
            EventMember(R.drawable.ic_account_circle_black_24dp, "Губайдуллин Андрей Евгеньевич"),
            EventMember(R.drawable.ic_account_circle_black_24dp, "Денисов Никита Михайлович"),
            EventMember(R.drawable.ic_account_circle_black_24dp, "Фокина Яна Игоревна"),
            EventMember(R.drawable.ic_account_circle_black_24dp, "Гордиенко Марк Александрович"),
            EventMember(R.drawable.ic_account_circle_black_24dp, "Ковалева Анастасия Павловна"),
            EventMember(R.drawable.ic_account_circle_black_24dp, "Половников Артем Евгеньевич"),
            EventMember(R.drawable.ic_account_circle_black_24dp, "Иконников Сергей Сергеевич")
    )

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_event_detail, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = EventDetailAdapter(userEntries)
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
