package com.example.student_attendance_ms.main.schedule.base

import android.provider.ContactsContract
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepository @Inject constructor(
        private val apiService: ApiService
){
    suspend fun fetchEvents(date: String): Flow<DataState<List<Event>>> = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.getEvents(date)
            emit(DataState.Success(result))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}