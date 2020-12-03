package com.example.student_attendance_ms.main.schedule.attendance

import androidx.lifecycle.*
import com.example.student_attendance_ms.network.service.ApiService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ScannerViewModel @AssistedInject constructor(
        private val apiService: ApiService,
        @Assisted private val eventId: Int
) : ViewModel() {

    private val _marked = MutableLiveData<Boolean>()
    val marked: LiveData<Boolean>
        get() = _marked

    fun provideCodeSending(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiService.sendQr(eventId.toString(), code)
            try {
                _marked.value = true
            } catch (e: Exception) {
                _marked.value = false
            }
        }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(
                eventId: Int
        ): ScannerViewModel
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