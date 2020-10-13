package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.model.AuthorizationResponse
import com.example.student_attendance_ms.login.User
import com.example.student_attendance_ms.login.UserX
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    // регистрация
    @POST("http://5.136.88.51:8000/")
    fun createUser(
            @Body() userX: UserX
    ): Call<ResponseBody>

    // авторизация
    @POST("login")
    fun login(
            @Body() user: User
    ): Call<AuthorizationResponse>

    // запрос списка событий
    @GET("events")
    suspend fun getEventsAsync(
        @Query("date") eventsByDate: String,
    ): Deferred<List<Event>>

}