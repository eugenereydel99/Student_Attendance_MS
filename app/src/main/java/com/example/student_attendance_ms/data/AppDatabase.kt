package com.example.student_attendance_ms.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.student_attendance_ms.utils.Constants

@Database(entities = [Event::class, Test::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
    abstract fun eventDao(): EventDao
    abstract fun testDao(): TestDao

    companion object{

        // синглтон
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            Constants.DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}