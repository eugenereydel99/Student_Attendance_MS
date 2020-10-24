package com.example.student_attendance_ms.main.schedule.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView;
import com.example.student_attendance_ms.databinding.ListEventMembersBinding
import com.example.student_attendance_ms.network.model.EventMember;

class EventDetailAdapter : ListAdapter<EventMember, EventDetailAdapter.EventDetailViewHolder>(DiffCallback) {

    class EventDetailViewHolder(
            private val binding: ListEventMembersBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(eventMember: EventMember) {
            binding.eventMember = eventMember
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EventDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListEventMembersBinding.inflate(
                layoutInflater, parent, false
        )
        return EventDetailViewHolder(binding)
    }

    override fun onBindViewHolder(
            holder: EventDetailViewHolder,
            position: Int
    ) = holder.bind(getItem(position))


    companion object DiffCallback : DiffUtil.ItemCallback<EventMember>() {
        override fun areItemsTheSame(oldItem: EventMember, newItem: EventMember): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EventMember, newItem: EventMember): Boolean {
            return oldItem.memberId == newItem.memberId
        }
    }
}