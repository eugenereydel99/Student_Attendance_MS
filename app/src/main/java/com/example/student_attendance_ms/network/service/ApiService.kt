package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.BuildConfig
import com.example.student_attendance_ms.login.AuthorizationRequest
import com.example.student_attendance_ms.login.AuthData
import com.example.student_attendance_ms.network.model.AuthorizationResponse
import com.example.student_attendance_ms.network.model.Event
import com.example.student_attendance_ms.network.model.User
import com.example.student_attendance_ms.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    // регистрация
    @POST("http://5.136.88.51:8000/")
    fun createUser(
            @Body() authorizationRequest: AuthorizationRequest): Call<ResponseBody>

    // авторизация
    @POST("login")
    fun login(
            @Body() user: AuthData
    ): Call<AuthorizationResponse>

    // запрос данных пользователя
    @GET("/user/{id}")
    suspend fun getUser(
            @Header("Authorization") authToken: String,
            @Path("id") userId: Int
    ): User

    // запрос списка событий
    @GET("events")
    suspend fun getEvents(
            @Header("Authorization") authToken: String,
            @Query("date") eventsByDate: String
    ): List<Event>


    companion object {

        private const val BASE_URL = "http://5.136.88.51:8000/"

        fun build(): ApiService {

            // логирование тела запроса/ответа только в режиме отладки
            val logger = HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            }

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
            // раскомментировать при использовании соединения по HTTPS
//            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))

            val retrofit: Retrofit.Builder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

            return retrofit.client(okHttpClient.build())
                    .build()
                    .create(ApiService::class.java)
        }
    }

}

