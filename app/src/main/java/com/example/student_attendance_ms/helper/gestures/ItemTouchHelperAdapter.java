package com.example.student_attendance_ms.helper.gestures;

public interface ItemTouchHelperAdapter {

    // вызывается при перемещении элемента
    boolean onItemMove(int fromPosition, int toPosition);

    // вызывается когда элемент был свайпнут
    void onItemDismiss(int position);
}
