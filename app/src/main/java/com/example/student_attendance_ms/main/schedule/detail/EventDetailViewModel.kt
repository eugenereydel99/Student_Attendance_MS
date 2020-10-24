package com.example.student_attendance_ms.main.schedule.detail

import android.content.Context
import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.EventMember
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.SessionManager
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class EventDetailViewModel @AssistedInject constructor(
        private val apiService: ApiService,
        @ActivityContext private val context: Context,
        @Assisted private val eventId: Int
) : ViewModel() {

    // получение токена аутентификации
    private val authToken = SessionManager(context).getToken()!!

    // переводим eventId в JSON

    private val _eventMembers = MutableLiveData<List<EventMember>>()
    val events: LiveData<List<EventMember>>
        get() = _eventMembers

    // проверка на наличие подписки у пользователя
    var isSubscribed: Boolean = false

    // запрос на получение списка участников события и статуса подписки
    init {
        viewModelScope.launch {
            val response = apiService.getEventMembers(authToken, eventId.toString())
            try {
                _eventMembers.value = response
//                isSubscribed = response.isSubscribed
            } catch (e: Exception) {
                _eventMembers.value = ArrayList()
            }
        }
    }

    // подписка на событие
    fun onEventSubscribe(){

    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(eventId: Int): EventDetailViewModel
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

