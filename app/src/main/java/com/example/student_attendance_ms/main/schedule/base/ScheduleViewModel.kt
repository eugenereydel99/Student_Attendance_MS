package com.example.student_attendance_ms.main.schedule.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.network.service.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel (
        authRepository: AuthRepository,
        application: Application
) : AndroidViewModel(application) {
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    // получение токена аутентификации
    private val authToken = authRepository.getToken()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // устанавливаем текущую дату
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    private val currentDate = dateFormat.format(Date()).toString()

    init {
        displayEventsByDate()
    }

    fun displayEventsByDate(date: String = currentDate, token: String? = authToken){
        coroutineScope.launch {
            val getEventsDeferred = ApiService.build(token).getEventsAsync(date)
            try {
                val listResult = getEventsDeferred.await()
                _events.value = listResult
            } catch (e: Exception){
                _events.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}