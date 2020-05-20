package com.example.student_attendance_ms.main.schedule.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScheduleViewModel : ViewModel() {
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val testData = mutableListOf(
            Event("1", "Криптография",
                    "ТУСУР", "10:40",
                    "12:15", "Якимук"
            ),
            Event("2", "ГПО",
                    "ТУСУР", "8:50",
                    "10:25", "Харченко"
            )
    )

    init {
        displayEventsByDate()
    }

    private fun displayEventsByDate(){
        coroutineScope.launch {
            _events.value = testData
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}