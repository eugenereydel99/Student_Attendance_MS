package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.main.schedule.base.Event
import com.example.student_attendance_ms.network.model.AuthorizationResponse
import com.example.student_attendance_ms.network.model.User
import com.example.student_attendance_ms.network.model.UserX
import com.example.student_attendance_ms.utils.Constants
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    // регистрация
    @POST(Constants.BASE_URL)
    fun createUser(
            @Body() userX: UserX
    ): Call<ResponseBody>

    // авторизация
    @POST("login")
    fun login(
            @Body() user: User
    ): Call<AuthorizationResponse>

    // запрос списка событий
//    @GET("events")
//    fun getEventsAsync(
//            @Query("date") date: String
//    ): Deferred<List<Event>>

    @GET("{eventsByDate}")
    fun getEventsAsync(
            @Path("eventsByDate") date: String
    ): Deferred<List<Event>>
}