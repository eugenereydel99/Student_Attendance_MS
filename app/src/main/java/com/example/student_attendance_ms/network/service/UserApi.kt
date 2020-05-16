package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.network.model.AuthorizationResponse
import com.example.student_attendance_ms.network.model.User
import com.example.student_attendance_ms.network.model.UserCredentials
import com.example.student_attendance_ms.utils.Constants
import com.google.zxing.qrcode.encoder.QRCode
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    // регистрация
    @POST(Constants.BASE_URL)
    fun createUser(
            @Body() user: User
    ): Call<ResponseBody>

    // авторизация
    @POST("login")
    fun login(
            @Body() userCredentials: UserCredentials
    ): Call<AuthorizationResponse>

    // отправка qr-кода
    @FormUrlEncoded
    @POST("qr")
    fun sendQR(
            @Field("qr_code") qr_code: String,
            @Query("auth_token") authToken: String?
    ): Call<ResponseBody>

}