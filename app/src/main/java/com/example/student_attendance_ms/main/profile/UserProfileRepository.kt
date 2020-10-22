package com.example.student_attendance_ms.main.profile

import androidx.lifecycle.LiveData
import com.example.student_attendance_ms.database.UserDao
import com.example.student_attendance_ms.network.model.User
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.UserProfileMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserProfileRepository @Inject constructor(
        private val apiService: ApiService,
        private val userDao: UserDao,
        private val userProfileMapper: UserProfileMapper
) {

    suspend fun getUser(authToken: String?, userId: Int): User {
        val userData = userDao.getUserId(userId.toString())

        if (userData.isEmpty()) {
            withContext(Dispatchers.IO) {
                val response = authToken?.let { apiService.getUser(it, userId) }
                response?.let { userProfileMapper.mapToEntity(it) }?.let {
                    userDao.save(
                            it
                    )
                }
            }
        }

        val result = userDao.load(userId.toString())
        return userProfileMapper.mapFromEntity(result)
    }

}