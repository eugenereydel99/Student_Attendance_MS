package com.example.student_attendance_ms.main.schedule.base

import com.example.student_attendance_ms.database.EventDao
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.EventMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository @Inject constructor(
        private val apiService: ApiService,
        private val eventDao: EventDao,
        private val eventMapper: EventMapper
){

    

}