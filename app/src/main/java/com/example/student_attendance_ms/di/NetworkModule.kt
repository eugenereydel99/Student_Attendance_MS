package com.example.student_attendance_ms.di

import com.example.student_attendance_ms.BuildConfig
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.storage.AuthenticationInterceptor
import com.example.student_attendance_ms.storage.IStorage
import com.example.student_attendance_ms.storage.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(includes = [StorageModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideSessionManager(storage: IStorage) = SessionManager(storage)

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
}