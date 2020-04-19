package com.example.student_attendance_ms.api.service

import com.example.student_attendance_ms.api.model.UserSignInResponse
import com.example.student_attendance_ms.api.model.UserSignUpResponse
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