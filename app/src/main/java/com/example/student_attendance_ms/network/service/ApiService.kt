package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    private const val BASE_URL = "http://91.105.146.185:3000/"

    @JvmStatic
    fun build(authToken: String? = null): UserApi{

        // логирование тела запроса/ответа
        val logger = HttpLoggingInterceptor()

        // использование логирования только при отладке приложения
        if (BuildConfig.DEBUG){
            logger.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
        // раскомментировать при использовании соединения по HTTPS
//            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))

        val retrofit: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        if (authToken != null){
            okHttpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                        .header("Authorization", authToken)
                        .build()

                chain.proceed(request)
            }
        }

        return retrofit.client(okHttpClient.build())
                .build()
                .create(UserApi::class.java)
    }


}






