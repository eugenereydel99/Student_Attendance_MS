package com.example.student_attendance_ms.main.schedule.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_attendance_ms.R;

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

        final TextView eventTime, eventTitle, eventType, eventLocation, eventTeacher;
        final CardView eventCard;
        final OnEventClickListener onEventClickListener;

        EventViewHolder(View itemView, OnEventClickListener onEventClickListener){
            super(itemView);
            this.onEventClickListener = onEventClickListener;
            eventCard = itemView.findViewById(R.id.event_card);
            eventTime = itemView.findViewById(R.id.event_time);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventType = itemView.findViewById(R.id.event_type);
            eventLocation = itemView.findViewById(R.id.event_location);
            eventTeacher = itemView.findViewById(R.id.event_teacher);

            itemView.setOnClickListener(this);
        }

        void bind(Event event){
            eventTime.setText(event.getTime());
            eventTitle.setText(event.getTitle());
            eventType.setText(event.getType());
            eventLocation.setText(event.getLocation());
            eventTeacher.setText(event.getTeacher());
        }

        @Override
        public void onClick(View v) {
            onEventClickListener.onEventClick(v, getAdapterPosition());
        }

    }

    @NonNull
    @Override
    public EventCardAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                R.layout.list_events, parent, false
                );
        return new EventViewHolder(view, onEventClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventCardAdapter.EventViewHolder holder, int position) {
        holder.bind(events.get(position));

//        holder.eventCard.setOnClickListener(v ->
//                Toast.makeText(context, "Position " + position, Toast.LENGTH_LONG).show()
//        );
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
