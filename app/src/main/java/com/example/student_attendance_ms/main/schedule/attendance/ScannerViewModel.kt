package com.example.student_attendance_ms.main.schedule.attendance

import androidx.lifecycle.*
import com.example.student_attendance_ms.network.model.AttendanceResult
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.DataState
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import java.net.ConnectException

class ScannerViewModel @AssistedInject constructor(
        private val apiService: ApiService,
        @Assisted private val eventId: Int
) : ViewModel() {

    private val _scanResult = MutableLiveData<DataState<AttendanceResult>>()
    val scanResult: LiveData<DataState<AttendanceResult>>
        get() = _scanResult

    fun provideCodeSending(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.sendQr(eventId.toString(), code)
                _scanResult.postValue(DataState.Success(response))
            } catch (exception : Exception){
                _scanResult.postValue(DataState.Error(exception))
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