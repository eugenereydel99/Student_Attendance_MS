package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.network.model.LoginResponse
import com.example.student_attendance_ms.network.model.User
import com.example.student_attendance_ms.network.model.UserCredentials
import com.example.student_attendance_ms.utils.Const
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    // регистрация
    @POST(Const.BASE_URL)
    fun createUser(
            @Body() user: User
    ): Call<ResponseBody>

    // авторизация
    @POST("login")
    fun login(
            @Body() userCredentials: UserCredentials
    ): Call<LoginResponse>

}