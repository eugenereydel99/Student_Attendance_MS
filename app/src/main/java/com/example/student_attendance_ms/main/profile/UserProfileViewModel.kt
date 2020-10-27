package com.example.student_attendance_ms.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_attendance_ms.network.model.User
import kotlinx.coroutines.launch

class UserProfileViewModel @ViewModelInject constructor(
        private val repository: UserProfileRepository
) : ViewModel() {

    private val _userParams = MutableLiveData<User>()
    val userParams: LiveData<User> = _userParams

    init {
        viewModelScope.launch {
            _userParams.value = repository.getUser()
        }
    }
}