package com.example.student_attendance_ms.main.schedule.base
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel @ViewModelInject constructor(
      private val repository: ScheduleRepository
) : ViewModel() {

    private val _dataState = MutableLiveData<DataState<List<Event>>>()
    val dataState: LiveData<DataState<List<Event>>>
        get() = _dataState

    // устанавливаем текущую дату
    private val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    private val currentDate = dateFormat.format(Date()).toString()

    init {
        displayEventsByDate()
    }

    fun displayEventsByDate(date: String = currentDate){
        viewModelScope.launch {
            repository.fetchEvents(date).collect {
                _dataState.value = it
            }
        }
    }

}