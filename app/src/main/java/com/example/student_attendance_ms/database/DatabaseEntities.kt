package com.example.student_attendance_ms.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.annotations.SerializedName

/*
* Сущность для хранения текущего пользователя
* */
@Entity(tableName = "user")
data class UserEntity constructor(
        @PrimaryKey(autoGenerate = false) val id: String,
        val email: String,
        val firstName: String,
        val secondName: String,
        val lastName: String,
        val createdAt: String,
        val updatedAt: String
)

@Dao
interface UserDao {

    @Query("SELECT id FROM user WHERE id = :userId")
    suspend fun getUserId(userId: String): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun load(userId: String): UserEntity
}

//------------------------------------------------------------------------------------------------//

/*
* Сущность для хранения списка событий
* */
@Entity(tableName = "events")
data class EventEntity constructor(
        @PrimaryKey(autoGenerate = false) val id: Int,
        val title: String,
        val location: String,
        val timeStart: String,
        val timeEnd: String,
        val creator: String,
        val comments: String,
        val date: String
)

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventEntity): Long

    @Query("SELECT * FROM events WHERE date = :date")
    suspend fun getEventsByDate(date: String): List<EventEntity>

}

//------------------------------------------------------------------------------------------------//

