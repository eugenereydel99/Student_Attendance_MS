package com.example.student_attendance_ms.main.schedule.attendance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.student_attendance_ms.network.service.ApiService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ScannerViewModel @AssistedInject constructor(
        private val apiService: ApiService,
        @Assisted private val eventId: Int
) : ViewModel() {

    lateinit var scanResult: String

    fun provideCodeSending(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = apiService.sendQr(eventId.toString(), code)
                request.enqueue(object : retrofit2.Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        scanResult = response.message().toString()
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable){
                        scanResult = t.message.toString()
                    }

                })
            } catch (e: Exception){
                scanResult = e.message.toString()
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