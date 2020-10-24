package com.example.student_attendance_ms.main.schedule.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.databinding.ListEventsBinding
import com.example.student_attendance_ms.network.model.Event

class EventAdapter : ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback) {

    /**
     * Создаём RecyclerView элементы
     */
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListEventsBinding.inflate(
                layoutInflater, parent, false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class EventViewHolder(
            private val binding: ListEventsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.event?.let { event ->
                    navigateToEvent(event, view)
                }
            }
        }

        fun bind(event: Event) {
            binding.event = event
            binding.executePendingBindings()
        }

        private fun navigateToEvent(event: Event, view: View) {
            val direction = ScheduleFragmentDirections
                    .actionScheduleFragmentToEventDetailFragment(event.id)
            view.findNavController().navigate(direction)
        }

    }

    /**
     * Позволяет RecyclerView определять, какой элемент был изменен, когда
     * список событий был обновлен
     * Нужен для того, чтобы не обновлять весь список
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }
    }
}