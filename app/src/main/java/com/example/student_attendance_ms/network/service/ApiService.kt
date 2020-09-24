package com.example.student_attendance_ms.network.service

import com.example.student_attendance_ms.utils.Constants
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    // логирование тела запроса/ответа
    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            // раскомментировать при использовании соединения по HTTPS
//            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))

    private val retrofit: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    @JvmStatic
    fun build(authToken: String? = null): UserApi{

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

        retrofit.client(okHttpClient.build())

        return retrofit.build().create(UserApi::class.java)
    }


}






