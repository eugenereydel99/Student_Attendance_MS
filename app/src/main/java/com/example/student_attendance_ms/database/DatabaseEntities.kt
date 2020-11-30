package com.example.student_attendance_ms.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.student_attendance_ms.network.model.Creator

/*
* Сущность для хранения текущего пользователя
* */
@Entity(tableName = "user")
data class UserEntity constructor(
        @PrimaryKey(autoGenerate = false) val id: String,
        val email: String,
        val group: String,
        val firstName: String,
        val secondName: String,
        val lastName: String
)

@Dao
interface UserDao {

    /*
    * Получаем пользователей из таблицы user
    * (он либо есть, либо его не существует)
    * */
    @Query("SELECT * FROM user")
    suspend fun getUser(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: UserEntity)

    /*
    * Загружаем пользователя в таблицу user
    * (необходимо для предотвращения повторной
    * отправки запроса пользователя на сервер)
    * */
    @Query("SELECT * FROM user")
    suspend fun load(): UserEntity
}

//------------------------------------------------------------------------------------------------//

///*
//* Сущность для хранения списка событий
//* */
//@Entity(tableName = "events")
//data class EventEntity constructor(
//        @PrimaryKey(autoGenerate = false) val id: Int,
//        val title: String,
//        val location: String,
//        val checkType: String,
//        val eventType: String,
//        val timeStart: String,
//        val timeEnd: String,
//        val creator: Creator,
//        val comments: String?,
//        val date: String
//)
//
//@Dao
//interface EventDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(event: EventEntity): Long
//
//    @Query("SELECT * FROM events WHERE date = :date")
//    suspend fun getEventsByDate(date: String): List<EventEntity>
//
//}

//------------------------------------------------------------------------------------------------//

