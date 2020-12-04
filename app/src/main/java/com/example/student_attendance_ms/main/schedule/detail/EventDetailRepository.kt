package com.example.student_attendance_ms.main.schedule.detail

import android.provider.ContactsContract
import com.example.student_attendance_ms.network.model.EventDetailResponse
import com.example.student_attendance_ms.network.model.EventVisitor
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventDetailRepository @Inject constructor(
        private val apiService: ApiService
) {
    suspend fun getEventParticipants(eventId: Int): Flow<DataState<EventDetailResponse>> = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.getEventMembers(eventId.toString())
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getEventVisitors(eventId: Int): Flow<DataState<List<EventVisitor>>> = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.getEventVisitors(eventId.toString())
            emit(DataState.Success(result))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    suspend fun subscribeOnEvent(eventId: Int) {
        withContext(Dispatchers.IO) {
            apiService.subscribeOnEvent(eventId.toString())
        }
    }

}