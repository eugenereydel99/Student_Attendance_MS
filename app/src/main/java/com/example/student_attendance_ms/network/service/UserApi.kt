package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.login.AuthorizationRequest
import com.example.student_attendance_ms.login.AuthData
import com.example.student_attendance_ms.network.model.AuthorizationResponse
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.model.User
import com.example.student_attendance_ms.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    // регистрация
    @POST(Constants.BASE_URL)
    fun createUser(
            @Body() authorizationRequest: AuthorizationRequest): Call<ResponseBody>

    // авторизация
    @POST("login")
    fun login(
            @Body() user: AuthData
    ): Call<AuthorizationResponse>

    // запрос данных пользователя
    @GET("/user/:{id}")
    fun getUser(
            @Path("id") userId: Int
    ): Call<User>

    // запрос списка событий
    @GET("events")
    suspend fun getEvents(
            @Query("date") eventsByDate: String,
    ): List<Event>

}