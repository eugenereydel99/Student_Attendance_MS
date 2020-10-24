package com.example.student_attendance_ms.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
        entities = [UserEntity::class, EventEntity::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun eventDao(): EventDao

    companion object{

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tmp = INSTANCE
            return tmp ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "student_ams_database"
                ).build()
                INSTANCE = instance
                return@synchronized instance
            }
        }
    }
}