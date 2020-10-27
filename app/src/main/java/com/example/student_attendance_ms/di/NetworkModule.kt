package com.example.student_attendance_ms.di

import android.content.Context
import com.example.student_attendance_ms.BuildConfig
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.storage.SessionManager
import com.example.student_attendance_ms.storage.SharedPreferencesStorage
import com.example.student_attendance_ms.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(includes = [
    StorageModule::class]
)
class NetworkModule {

    @Singleton
    @Provides
    fun provideSessionManager(storage: Storage) = SessionManager(storage)

    @Singleton
    @Provides
    fun provideApiService(sessionManager: SessionManager): ApiService {

        val accessToken = sessionManager.getAccessToken()

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

        if (accessToken != null) {
            val authInterceptor = Interceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                        .header("Authorization", accessToken)
                        .build()

                chain.proceed(request)
            }

            okHttpClient.addInterceptor(authInterceptor)
        }


        return Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ApiService::class.java)
    }

}