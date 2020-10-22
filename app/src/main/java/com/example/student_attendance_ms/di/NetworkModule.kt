package com.example.student_attendance_ms.di
import com.example.student_attendance_ms.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule{

    @Singleton
    @Provides
    fun provideApiService() = ApiService.build()
}