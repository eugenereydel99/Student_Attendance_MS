package com.example.student_attendance_ms.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface EventDao {

    @Query("SELECT * FROM events ORDER BY time_start ASC")
    fun getEvents(): LiveData<List<Event>>

    @Query("SELECT * FROM events WHERE event_id = :eventID")
    fun getEvent(eventID: String): LiveData<Event>

}