package com.example.student_attendance_ms.main.profile

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.student_attendance_ms.database.UserDb
import com.example.student_attendance_ms.utils.SessionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
//
//class UserProfileViewModel @ViewModelInject constructor(
//        private val repository: UserProfileRepository,
//        @ApplicationContext private val context: Context
//): ViewModel() {
//
//    private val sessionManager = SessionManager(context)
//    private val authParams = Pair(
//            first = sessionManager.getUserId(),
//            second = sessionManager.getToken()
//    )
//
//    private val _userParams = MutableLiveData<UserDb>()
//    val userParams: LiveData<UserDb> = _userParams
//
//    init {
//        viewModelScope.launch {
//            _userParams.value = repository.getUser(
//                    authParams.first,
//                    authParams.second)
//        }
//    }
//}