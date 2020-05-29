package com.example.student_attendance_ms.main.schedule.base

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.student_attendance_ms.network.service.UserApiService
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel : ViewModel() {
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // устанавливаем текущую дату
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    private val currentDate = dateFormat.format(Date()).toString()

//    private val testData = mutableListOf(
//            Event("1", "Криптография",
//                    "ТУСУР", "10:40",
//                    "12:15", "Якимук"
//            ),
//            Event("2", "ГПО",
//                    "ТУСУР", "8:50",
//                    "10:25", "Харченко"
//            )
//    )

    init {
        displayEventsByDate()
    }

    fun displayEventsByDate(date: String = currentDate){
        coroutineScope.launch {
//            _events.value = testData
            val getEventsDeferred = UserApiService.retrofitService.getEventsAsync("")
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