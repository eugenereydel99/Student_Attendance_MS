package com.example.student_attendance_ms.helper.gestures;

public interface ItemTouchHelperViewHolder {

    // вызывается, когда ItemTouchHelper впервые регистрирует элемент как перемещаемый
    void onItemSelected();

    // вызывается, когда ItemTouchHelper завершил перемещение или свайп
    void onItemClear();
}
