package com.example.student_attendance_ms.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.student_attendance_ms.network.model.User

class UserProfileViewModel(
        savedStateHandle: SavedStateHandle
): ViewModel() {
    val userId: String = savedStateHandle[":uid"] ?:
            throw kotlin.IllegalArgumentException("missing user id")
    val user: LiveData<User> = TODO()


}