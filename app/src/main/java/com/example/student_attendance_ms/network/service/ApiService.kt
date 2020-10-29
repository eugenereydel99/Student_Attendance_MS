package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.login.AuthorizationRequest
import com.example.student_attendance_ms.login.AuthData
import com.example.student_attendance_ms.login.AuthorizationResponse
import com.example.student_attendance_ms.network.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // регистрация
    @POST("http://37.21.141.75:8000/")
    @Headers("No-Authentication: true")
    fun createUser(
            @Body() authorizationRequest: AuthorizationRequest
    ): Call<ResponseBody>

    // авторизация
    @POST("login")
    @Headers("No-Authentication: true")
    fun login(
            @Body() user: AuthData
    ): Call<AuthorizationResponse>

    // запрос данных пользователя
    @GET("user")
    suspend fun getUser(): User

    // запрос списка событий
    @GET("events")
    suspend fun getEvents(
            @Query("date") eventsByDate: String
    ): List<Event>

    // запрос списка участников событий
    @GET("events/{id}/participants")
    suspend fun getEventMembers(
            @Path("id") eventId: String
    ): List<EventMember>

    // запрос подписки на событие
    @GET("events/event_id")
    suspend fun subscribeOnEvent(
            @Body() eventId: String
    ): ResponseBody


    companion object {
        const val BASE_URL = "http://37.21.141.75:8000/"
    }
}


