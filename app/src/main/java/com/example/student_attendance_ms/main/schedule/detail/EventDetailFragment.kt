package com.example.student_attendance_ms.main.schedule.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentEventDetailBinding
import com.example.student_attendance_ms.network.model.EventDetailResponse
import com.example.student_attendance_ms.network.model.EventMember
import com.example.student_attendance_ms.utils.DataState
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

        attachObservers()

        return binding.root

    }

    private fun attachObservers(){
        eventDetailViewModel.dataState.observe(viewLifecycleOwner){ dataState ->
            when (dataState){
                is DataState.Success<EventDetailResponse> -> {
                    displayProgressBar(false)
                    submitEventMemberList(dataState.data.eventMembers)
                    onEventSubscribed(dataState.data.isSubscribed)
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
        if (!progressBar.isVisible) binding.onEventSubscribe.visibility = View.VISIBLE
    }

    private fun onEventSubscribed(isSubscribed: Boolean){
        val button = binding.onEventSubscribe
        if (isSubscribed){
            button.text = resources.getString(R.string.pass_attendance)
            button.setOnClickListener {
                val direction = EventDetailFragmentDirections
                        .actionEventDetailFragmentToScannerFragment(args.eventId)
                this@EventDetailFragment.findNavController().navigate(direction)
            }
        } else {
            button.text = resources.getString(R.string.subscribe_string)
            button.setOnClickListener {
                eventDetailViewModel.subscribeOnEvent()
            }
        }
    }

    private fun submitEventMemberList(eventMembers: List<EventMember>){
        viewAdapter.submitList(eventMembers)
    }
}
