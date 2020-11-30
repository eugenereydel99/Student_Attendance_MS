package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.login.AuthData
import com.example.student_attendance_ms.login.AuthorizationResponse
import com.example.student_attendance_ms.network.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // регистрация
    @POST("register")
    @Headers("No-Authentication: true")
    fun createUser(
            @Body() authorizationRequest: AuthData
    ): Call<ResponseBody>

    // авторизация
    @POST("login")
    @Headers("No-Authentication: true")
    fun login(
            @Body() user: AuthData
    ): Call<AuthorizationResponse>

    // запрос данных пользователя
    @GET("users")
    suspend fun getUser(): User

    // запрос списка событий
    @GET("events")
    suspend fun getEvents(
            @Query("date") eventsByDate: String
    ): List<Event>

    // запрос списка участников событий
    @GET("events/{id}/users")
    suspend fun getEventMembers(
            @Path("id") eventId: String
    ): EventDetailResponse

    // запрос подписки на событие
    @POST("events/{eventId}/users")
    suspend fun subscribeOnEvent(
            @Path("eventId") eventId: String
    ): ResponseBody

    companion object {
        const val BASE_URL = "http://591c8745feab.ngrok.io/"
    }
}


