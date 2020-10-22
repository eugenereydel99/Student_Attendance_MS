package com.example.student_attendance_ms.di

import android.content.Context
import com.example.student_attendance_ms.database.AppDatabase
import com.google.gson.annotations.Expose
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideUser(appDatabase: AppDatabase) = appDatabase.userDao()

}