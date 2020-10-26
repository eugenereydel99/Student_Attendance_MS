package com.example.student_attendance_ms.network.service

import android.content.Context
import android.content.SharedPreferences
import com.example.student_attendance_ms.BuildConfig
import com.example.student_attendance_ms.di.AssistedInject_AssistedInjectModule
import com.example.student_attendance_ms.di.NetworkModule
import com.example.student_attendance_ms.login.AuthorizationRequest
import com.example.student_attendance_ms.login.AuthData
import com.example.student_attendance_ms.login.AuthorizationResponse
import com.example.student_attendance_ms.network.model.*
import com.example.student_attendance_ms.utils.SessionManager
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.inject.Singleton

interface ApiService {

    // регистрация
    @POST("http://37.21.141.75:8000/")
    fun createUser(
            @Body() authorizationRequest: AuthorizationRequest
    ): Call<ResponseBody>

    // авторизация
    @POST("login")
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

        private const val BASE_URL = "http://37.21.141.75:8000/"

        fun build(sessionManager: SessionManager): ApiService {

            val authToken = sessionManager.getToken()

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


            if (authToken != null){
                val authInterceptor = Interceptor { chain ->
                    var request = chain.request()
                    request = request.newBuilder()
                            .header("Authorization", authToken)
                            .build()

                    chain.proceed(request)
                }

                okHttpClient.addInterceptor(authInterceptor)
            }


            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build()
                    .create(ApiService::class.java)
        }
    }

}

