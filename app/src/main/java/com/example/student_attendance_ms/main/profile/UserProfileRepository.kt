package com.example.student_attendance_ms.main.profile

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

    suspend fun getUser(): User {

        // получаем информацию из локальной базы данных о наличии пользователя
        val userData = userDao.getUser()

        // если локальная БД пуста, выполняем запрос к серверу
        if (userData.isEmpty()) {
            withContext(Dispatchers.IO) {
                val response = apiService.getUser()
                userDao.save(userProfileMapper.mapToEntity(response))
            }
        }

        // заново пытаемся получить данные из локальной БД
        val result = userDao.load()
        return userProfileMapper.mapFromEntity(result)
    }

}