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

    // запрос списка отмеченных участников события
    @GET("events/{eventId}/visitors")
    suspend fun getEventVisitors(
            @Path("eventId") eventId: String
    ): List<EventVisitor>

    // запрос подписки на событие
    @POST("events/{eventId}/users")
    suspend fun subscribeOnEvent(
            @Path("eventId") eventId: String
    ): ResponseBody

    // проверка QR-кода
    @POST("events/{eventId}/code")
    suspend fun sendQr(
            @Path("eventId") eventId: String,
            @Query("code") code: String
    )

    companion object {
        const val BASE_URL = "http://207.154.210.81/"
    }
}


