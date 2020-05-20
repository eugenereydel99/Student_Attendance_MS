package com.example.student_attendance_ms.main.schedule.base

data class Event(
        val id: String,
        val title: String,
        val location: String,
        val timeStart: String,
        val timeEnd: String,
        val creatorName: String,
        val comments: String? = null
)