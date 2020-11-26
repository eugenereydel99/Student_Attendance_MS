package com.example.student_attendance_ms.main.schedule.base

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.ListEventsBinding
import com.example.student_attendance_ms.network.model.Event

class EventAdapter(
        private val context: Fragment
) : ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback) {
    
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

    inner class EventViewHolder(
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
            when (event.type) {
                context.getString(R.string.lecture) -> binding.eventCard.setCardBackgroundColor(
                        ResourcesCompat.getColor(context.resources, R.color.pistachio, null)
                )
                context.getString(R.string.standard_practice) -> binding.eventCard.setCardBackgroundColor(
                        ResourcesCompat.getColor(context.resources, R.color.pistachio, null)
                )
                context.getString(R.string.educational_practice) -> binding.eventCard.setCardBackgroundColor(
                        ResourcesCompat.getColor(context.resources, R.color.pistachio, null)
                )
                context.getString(R.string.lab) -> binding.eventCard.setCardBackgroundColor(
                        ResourcesCompat.getColor(context.resources, R.color.pistachio, null)
                )
                context.getString(R.string.coursework) -> binding.eventCard.setCardBackgroundColor(
                        ResourcesCompat.getColor(context.resources, R.color.pistachio, null)
                )
                else -> binding.eventCard.setCardBackgroundColor(Color.WHITE)
            }
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