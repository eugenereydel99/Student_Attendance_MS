package com.example.student_attendance_ms.main.profile

import com.example.student_attendance_ms.database.UserDao
import com.example.student_attendance_ms.database.UserDb
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//
//class UserProfileRepository @Inject constructor(
//        private val apiService: ApiService,
//        private val userDao: UserDao
//) {
//    suspend fun getUser(userId: Int, authToken: String?): UserDb {
//        val userData = userDao.getUserId(userId.toString())
//
//        if (userData.isEmpty()) {
//            withContext(Dispatchers.IO) {
//                val response = apiService.build(authToken).getUser(userId)
//                userDao.save(response.asDatabaseModel())
//            }
//        }
//        return userDao.load(userId.toString())
//    }
//}