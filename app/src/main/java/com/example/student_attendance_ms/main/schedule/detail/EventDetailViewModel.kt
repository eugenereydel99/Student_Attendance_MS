package com.example.student_attendance_ms.main.schedule.detail

import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.EventDetailResponse
import com.example.student_attendance_ms.utils.DataState
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

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

    fun onEventDetailStateListener(eventDetailState: EventDetailState){
        when (eventDetailState){
            EventDetailState.GetEventParticipants -> {
                getEventMembers()
            }
            EventDetailState.SubscribeOnEvent -> {
                subscribeOnEvent()
            }
            EventDetailState.GetEventVisitors -> {}
        }
    }


    private fun getEventMembers(){
        viewModelScope.launch {
            repository.getEventParticipants(eventId).collect {
                _dataState.value = it
            }
        }
    }

    private fun subscribeOnEvent(){
        viewModelScope.launch {
            try {
                repository.subscribeOnEvent(eventId)
                repository.getEventParticipants(eventId).collect {
                    _dataState.value = it
                }
            } catch (e: Exception){
                _dataState.value = DataState.Error(e)
            }
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

