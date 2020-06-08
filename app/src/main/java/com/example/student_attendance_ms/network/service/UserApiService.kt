package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor).build()

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create()) // отправляем
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // получаем
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()

object UserApiService{
    val retrofitService: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
}

