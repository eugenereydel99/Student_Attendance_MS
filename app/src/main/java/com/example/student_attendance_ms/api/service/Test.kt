package com.example.student_attendance_ms.api.service

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://e04de493.ngrok.io/"

private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

interface UserApiServiceTest{
    @FormUrlEncoded
    @POST(BASE_URL)
    fun createUser(
            @Field("email") email: String,
            @Field("password") password: String
    ): Call<ResponseBody>

}

// singleton
object UserApiTest{
    val retrofitService: UserApiServiceTest by lazy {
        retrofit.create(UserApiServiceTest::class.java)
    }
}