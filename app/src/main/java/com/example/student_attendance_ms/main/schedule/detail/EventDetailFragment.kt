package com.example.student_attendance_ms.main.schedule.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R
import androidx.navigation.fragment.navArgs
import com.example.student_attendance_ms.databinding.FragmentEventDetailBinding
import com.example.student_attendance_ms.main.schedule.base.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */

@AndroidEntryPoint
class EventDetailFragment : Fragment() {

    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewAdapter: EventDetailAdapter
    // ининциализируем переменную, в которой будет храниться eventId
    private val args: EventDetailFragmentArgs by navArgs()

    // внедряем ViewModelFactory, сгенерированную при помощи AssistedInject
    @Inject
    lateinit var eventDetailViewModelFactory: EventDetailViewModel.AssistedFactory

    // инициализируем EventDetailViewModel используя ViewModelProvider.Factory
    private val eventDetailViewModel: EventDetailViewModel by viewModels{
        EventDetailViewModel.provideFactory(
                eventDetailViewModelFactory,
                args.eventId
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = eventDetailViewModel

        // инициализируем адаптер
        viewAdapter = EventDetailAdapter()
        binding.eventMembersRecyclerView.adapter = viewAdapter

        // отображаем список участников события в RecyclerView
        eventDetailViewModel.events.observe(viewLifecycleOwner){
            viewAdapter.submitList(it)
        }

        eventDetailViewModel.isSubscribed.observe(viewLifecycleOwner) {
            if (!it) {
                binding.onEventSubscribe.setOnClickListener {
                    eventDetailViewModel.onEventSubscribe()
                }
            }
        }

        return binding.root

    }


}
