package com.example.student_attendance_ms.main.schedule.detail

import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.Creator
import com.example.student_attendance_ms.network.model.EventMember
import com.example.student_attendance_ms.network.service.ApiService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.lang.Exception

class EventDetailViewModel @AssistedInject constructor(
        private val apiService: ApiService,
        @Assisted private val eventId: Int
) : ViewModel() {

    private val _eventMembers = MutableLiveData<List<EventMember>>()
    val events: LiveData<List<EventMember>>
        get() = _eventMembers

    // проверка на наличие подписки у пользователя
    private val _isSubscribed = MutableLiveData<Boolean>()
    val isSubscribed: LiveData<Boolean>
        get() = _isSubscribed


    // запрос на получение списка участников события и статуса подписки
    init {
        viewModelScope.launch {
            val response = apiService.getEventMembers(eventId.toString())
            try {
                _eventMembers.value = response.eventMembers
                _isSubscribed.value = response.isSubscribed
            } catch (e: Exception) {
                _eventMembers.value = ArrayList()
            }
        }
    }

    // подписка на событие
    fun onEventSubscribe(){
        viewModelScope.launch {
            val response = apiService.subscribeOnEvent(eventId.toString())
        }

    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(
                eventId: Int
        ): EventDetailViewModel
    }

    companion object {
        fun provideFactory(
                assistedFactory: AssistedFactory,
                eventId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(eventId) as T
            }
        }
    }
}

