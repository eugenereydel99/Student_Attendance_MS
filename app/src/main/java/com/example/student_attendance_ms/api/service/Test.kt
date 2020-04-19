package com.example.student_attendance_ms.api.service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://blooming-wave-18737.herokuapp.com/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

interface UserApiServiceTest{
    @FormUrlEncoded
    @POST("users/new")
    fun createUser(
            @Field("login") login: String,
            @Field("password") password: String,
            @Field("role") role: String
    ): Call<String>

    @FormUrlEncoded
    @POST("login")
    fun login(
            @Field("login") login: String,
            @Field("password") password: String
    ): Call<String>

}

// singleton
object UserApiTest{
    val retrofitService: UserApiServiceTest by lazy {
        retrofit.create(UserApiServiceTest::class.java)
    }
}