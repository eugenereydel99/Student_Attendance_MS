package com.example.student_attendance_ms.main.schedule.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScheduleViewModelFactory (
        private val authToken: String?,
        private val application: Application
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScheduleViewModel(authToken, application) as T
    }
}