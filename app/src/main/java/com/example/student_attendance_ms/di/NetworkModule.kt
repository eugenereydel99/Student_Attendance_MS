package com.example.student_attendance_ms.di
import android.content.Context
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.SessionManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApiService::class)
@Module
class NetworkModule{

    @Singleton
    @Provides
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

    @Singleton
    @Provides
    fun provideApiService(sessionManager: SessionManager)
            = ApiService.build(sessionManager)

}