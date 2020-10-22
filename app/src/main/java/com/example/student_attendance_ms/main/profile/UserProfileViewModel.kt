package com.example.student_attendance_ms.main.profile

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.student_attendance_ms.database.CurrentUser
import com.example.student_attendance_ms.network.model.User
import com.example.student_attendance_ms.utils.SessionManager
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class UserProfileViewModel @ViewModelInject constructor(
        private val repository: UserProfileRepository,
        @ActivityContext private val context: Context
): ViewModel() {

    private val sessionManager = SessionManager(context)
    private val authParams = Pair(
            first = sessionManager.getToken(),
            second = sessionManager.getUserId()
    )

    private val _userParams = MutableLiveData<User>()
    val userParams: LiveData<User> = _userParams

    init {
        viewModelScope.launch {
            _userParams.value = repository.getUser(
                    authParams.first,
                    authParams.second)
        }
    }
}