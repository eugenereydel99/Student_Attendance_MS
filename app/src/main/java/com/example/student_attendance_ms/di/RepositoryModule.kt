package com.example.student_attendance_ms.di

import com.example.student_attendance_ms.database.EventDao
import com.example.student_attendance_ms.database.UserDao
import com.example.student_attendance_ms.main.profile.UserProfileRepository
import com.example.student_attendance_ms.main.schedule.base.EventRepository
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.EventMapper
import com.example.student_attendance_ms.utils.UserProfileMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Singleton

@InstallIn(FragmentComponent::class)
@Module
class RepositoryModule {

    @FragmentScoped
    @Provides
    fun provideUserProfileRepository(
            apiService: ApiService,
            userDao: UserDao,
            userProfileMapper: UserProfileMapper
    ): UserProfileRepository = UserProfileRepository(
            apiService, userDao, userProfileMapper
    )

    @FragmentScoped
    @Provides
    fun provideEventRepository(
            apiService: ApiService,
            eventDao: EventDao,
            eventMapper: EventMapper
    ): EventRepository = EventRepository(
            apiService, eventDao, eventMapper
    )

}