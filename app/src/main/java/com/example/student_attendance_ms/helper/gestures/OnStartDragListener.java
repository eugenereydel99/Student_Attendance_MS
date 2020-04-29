package com.example.student_attendance_ms.helper.gestures;

import androidx.recyclerview.widget.RecyclerView;

public interface OnStartDragListener {
    // вызывается, когда происходит касание по элементу и начало его передвижения
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
