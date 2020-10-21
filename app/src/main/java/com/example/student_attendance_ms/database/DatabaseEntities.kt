package com.example.student_attendance_ms.database

import androidx.room.*

// сущность для хранения текущего пользователя
@Entity(tableName = "user")
data class UserDb constructor(
        @PrimaryKey (autoGenerate = false) val id: String,
        val email: String,
        val firstName: String,
        val secondName: String,
        val lastName: String,
        val createdAt: String,
        val updatedAt: String,
)

@Dao
interface UserDao {

    @Query("SELECT id FROM user WHERE id = :userId")
    fun getUserId(userId: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(userDb: UserDb)

    @Query("SELECT * FROM user WHERE id = :userId")
    fun load(userId: String): UserDb
}

