package com.example.student_attendance_ms.login.api.service

import com.example.student_attendance_ms.login.api.model.UserSignInResponse
import com.example.student_attendance_ms.login.api.model.UserSignUpResponse
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApiService {
    // регистрация
    @FormUrlEncoded
    @POST("users/sign_up")
    fun createUser(
            @Field("email") login: String,
            @Field("password") password: String,
            @Field("role") role: String
    ): Call<UserSignUpResponse>

    // авторизация
    @FormUrlEncoded
    @POST("users/sign_in")
    fun login(
            @Field("email") login: String,
            @Field("password") password: String
    ): Call<UserSignInResponse>

}