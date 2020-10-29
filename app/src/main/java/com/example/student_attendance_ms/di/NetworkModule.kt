package com.example.student_attendance_ms.di

import com.example.student_attendance_ms.BuildConfig
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.storage.AuthenticationInterceptor
import com.example.student_attendance_ms.storage.SessionManager
import com.example.student_attendance_ms.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(includes = [StorageModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideSessionManager(storage: Storage) = SessionManager(storage)

    @Singleton
    @Provides
    fun provideAuthInterceptor(sessionManager: SessionManager) = AuthenticationInterceptor(sessionManager)

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthenticationInterceptor): OkHttpClient {
        val logger = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                this.addInterceptor(logger)
            } else {
                this.connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
            }
        }.addInterceptor(authInterceptor)

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

//    @Singleton
//    @Provides
//    fun provideApiService(sessionManager: SessionManager): ApiService {
//
//        val accessToken = sessionManager.getAccessToken()
//
//        // логирование тела запроса/ответа только в режиме отладки
//        val logger = HttpLoggingInterceptor().apply {
//            if (BuildConfig.DEBUG) {
//                this.level = HttpLoggingInterceptor.Level.BODY
//            }
//        }
//
//        val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(logger)
//        // раскомментировать при использовании соединения по HTTPS
////            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
//
//        if (accessToken != null) {
//            val authInterceptor = Interceptor { chain ->
//                var request = chain.request()
//                request = request.newBuilder()
//                        .header("Authorization", accessToken)
//                        .build()
//
//                chain.proceed(request)
//            }
//
//            okHttpClient.addInterceptor(authInterceptor)
//        }
//
//
//        return Retrofit.Builder()
//                .baseUrl(ApiService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient.build())
//                .build()
//                .create(ApiService::class.java)
//    }

}