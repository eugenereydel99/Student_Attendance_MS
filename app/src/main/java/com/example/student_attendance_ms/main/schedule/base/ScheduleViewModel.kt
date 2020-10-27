package com.example.student_attendance_ms.main.schedule.base
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.service.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel @ViewModelInject constructor(
      private val apiService: ApiService
) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    // устанавливаем текущую дату
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    private val currentDate = dateFormat.format(Date()).toString()

    init {
        displayEventsByDate()
    }

    fun displayEventsByDate(date: String = currentDate){
        viewModelScope.launch {
            val getEvents = apiService.getEvents(date)
            try {
                _events.value = getEvents
            } catch (e: Exception){
                _events.value = ArrayList()
            }
        }
    }

}