package com.example.student_attendance_ms.main.schedule.detail

import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.Creator
import com.example.student_attendance_ms.network.model.EventDetailResponse
import com.example.student_attendance_ms.network.model.EventMember
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.DataState
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

sealed class EventDetailState{
    object GetEventParticipants: EventDetailState()
    object SubscribeOnEvent: EventDetailState()
    object GetEventVisitors: EventDetailState()
}

class EventDetailViewModel @AssistedInject constructor(
        private val repository: EventDetailRepository,
        @Assisted private val eventId: Int
) : ViewModel() {

    private val _dataState = MutableLiveData<DataState<EventDetailResponse>>()
    val dataState: LiveData<DataState<EventDetailResponse>>
        get() = _dataState

    init {
        viewModelScope.launch {
            repository.getEventParticipants(eventId).collect {
                _dataState.value = it
            }
        }
    }

    fun subscribeOnEvent(){
        viewModelScope.launch {
            repository.subscribeOnEvent(eventId)
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

