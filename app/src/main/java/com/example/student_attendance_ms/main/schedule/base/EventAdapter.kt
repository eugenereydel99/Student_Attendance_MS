package com.example.student_attendance_ms.main.schedule.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendance_ms.databinding.ListEventsBinding
import com.example.student_attendance_ms.network.model.Event

class EventAdapter (
    private val clickListener: OnClickListener
): ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback) {

    class EventViewHolder(
            private val binding: ListEventsBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: OnClickListener, event: Event){
            binding.event = event
//            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

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
        holder.bind(clickListener, event)
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

    /**
     * Кастомный listener, который перехватывает клики RecyclerView элементов.
     */
    class OnClickListener(val clickListener: (event: Event) -> Unit) {
        fun onClick(event: Event) = clickListener(event)
    }

}