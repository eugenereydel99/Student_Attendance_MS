package com.example.student_attendance_ms.di

import com.example.student_attendance_ms.database.UserDao
import com.example.student_attendance_ms.main.profile.UserProfileRepository
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.UserProfileMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class RepositoryModule {

    @ActivityScoped
    @Provides
    fun provideUserProfileRepository(
            apiService: ApiService,
            userDao: UserDao,
            userProfileMapper: UserProfileMapper
    ): UserProfileRepository = UserProfileRepository(
            apiService, userDao, userProfileMapper
    )

}