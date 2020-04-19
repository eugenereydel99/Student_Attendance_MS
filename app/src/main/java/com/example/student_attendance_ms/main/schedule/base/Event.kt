package com.example.student_attendance_ms.main.schedule.base

data class Event(
    val time: String,
    val title: String,
    val type: String,
    val location: String,
    val teacher: String,
    val extra: String? = null
)