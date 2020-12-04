package com.example.student_attendance_ms.utils

import java.lang.Exception

sealed class DataState<out R> {
    data class Success<out T : Any>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}