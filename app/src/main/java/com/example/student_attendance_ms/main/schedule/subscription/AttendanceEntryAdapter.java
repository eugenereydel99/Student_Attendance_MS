package com.example.student_attendance_ms.main.schedule.subscription;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_attendance_ms.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttendanceEntryAdapter extends RecyclerView.Adapter<AttendanceEntryAdapter.UserEntriesViewHolder> {

    private List<UserEntry> userEntries;

    public AttendanceEntryAdapter (List<UserEntry> userEntries){
        this.userEntries = userEntries;
    }

    @NonNull
    @Override
    public UserEntriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_user_entries,
                parent,
                false
        );
        return new UserEntriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserEntriesViewHolder holder, int position) {
        holder.bind(userEntries.get(position));
    }

    @Override
    public int getItemCount() {
        return userEntries.size();
    }

    static class UserEntriesViewHolder extends RecyclerView.ViewHolder{

        final CircleImageView userImage;
        final TextView userName;

        UserEntriesViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.person_name);
        }

        void bind(UserEntry userEntries){
            userImage.setImageResource(userEntries.getUserImage());
            userName.setText(userEntries.getUserName());
        }
    }

}
