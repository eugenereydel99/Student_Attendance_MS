package com.example.student_attendance_ms.network.service

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.student_attendance_ms.BuildConfig
import com.example.student_attendance_ms.utils.Constants

object ApiService {

    fun build(authToken: String? = null): UserApi{

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
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

//        if (authToken != null){
//            okHttpClient.addInterceptor { chain ->
//                val original = chain.request()
//                val request = original.newBuilder()
//                        .header("Authorization", authToken)
//                        .build()
//
//                chain.proceed(request)
//            }
//        }

        return retrofit.client(okHttpClient.build())
                .build()
                .create(UserApi::class.java)
    }


}






