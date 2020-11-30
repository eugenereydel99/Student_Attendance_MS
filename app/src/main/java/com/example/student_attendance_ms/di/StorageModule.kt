package com.example.student_attendance_ms.di

import com.example.student_attendance_ms.storage.IStorage
import com.example.student_attendance_ms.storage.SharedPreferencesStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class StorageModule{

    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): IStorage
}