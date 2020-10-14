package com.example.student_attendance_ms.main.profile

import androidx.lifecycle.LiveData
import com.example.student_attendance_ms.database.UserDao
import com.example.student_attendance_ms.database.User
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.network.service.UserApi
import java.util.concurrent.Executor

class UserProfileRepository(
        private val userApi: UserApi,
        private val userDao: UserDao
) {

    fun getUser(userId: String, authToken: String): LiveData<User>{
        refreshUser(userId, authToken)
        return userDao.load(userId)
    }

    private fun refreshUser(userId: String, authToken: String){
        val userData = userDao.getId(userId)

        val response = ApiService.build(authToken).getUser(userId.toInt())

        // здесь можно проверять на ошибки

        // обновление базы данных
//        userDao.save(response.body)
    }

}