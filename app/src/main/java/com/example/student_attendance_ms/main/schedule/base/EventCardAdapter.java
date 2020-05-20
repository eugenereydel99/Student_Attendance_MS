package com.example.student_attendance_ms.main.schedule.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_attendance_ms.R;
import com.example.student_attendance_ms.databinding.ListEventsBinding;

import java.util.List;

public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.EventViewHolder> {

    private List<Event> events;
    private OnEventClickListener onEventClickListener;

    public interface OnEventClickListener{
        void onEventClick(View view, int position);
    }

    public EventCardAdapter(List<Event> events, OnEventClickListener onEventClickListener){
        this.events = events;
        this.onEventClickListener = onEventClickListener;
    }

    static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ListEventsBinding binding;
        final OnEventClickListener onEventClickListener;

        EventViewHolder(View itemView, OnEventClickListener onEventClickListener){
            super(itemView);
            this.onEventClickListener = onEventClickListener;
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onEventClickListener.onEventClick(v, getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public EventCardAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListEventsBinding binding = ListEventsBinding.inflate(
                layoutInflater,parent,false
        );
        return new EventViewHolder(binding.getRoot(), onEventClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventCardAdapter.EventViewHolder holder, int position) {
        holder.binding.setEvent(events.get(position));
    }


    @Override
    public int getItemCount() {
        return events.size();
    }
}
