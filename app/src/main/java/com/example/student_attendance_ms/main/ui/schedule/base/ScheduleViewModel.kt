package com.example.student_attendance_ms.main.ui.schedule.base
import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModelFactory (
        private val authToken: String?
): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScheduleViewModel(authToken) as T
    }
}

class ScheduleViewModel (
        private val authToken: String?
) : ViewModel() {
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>>
        get() = _events

    // получение токена аутентификации


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