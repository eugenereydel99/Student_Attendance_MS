package com.example.student_attendance_ms.main.schedule.base
import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.SessionManager
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel @ViewModelInject constructor(
      @ActivityContext private val context: Context,
      private val apiService: ApiService
) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    private val authToken = SessionManager(context).getToken()

    // устанавливаем текущую дату
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    private val currentDate = dateFormat.format(Date()).toString()

    init {
        displayEventsByDate()
    }

    fun displayEventsByDate(date: String = currentDate, token: String? = authToken){
        viewModelScope.launch {
            val getEvents = token?.let { apiService.getEvents(it, date) }
            try {
                _events.value = getEvents
            } catch (e: Exception){
                _events.value = ArrayList()
            }
        }
    }

}