package com.example.student_attendance_ms.data

import androidx.room.*
import java.util.*

@Entity(tableName = "events")
data class Event(
        @PrimaryKey @ColumnInfo(name = "event_id") val eventID: String,
        val title: String,
        @ColumnInfo(name = "qr") val qrCode: String,
        val location: String,
        @ColumnInfo(name = "time_start") val timeStart: Date?,
        @ColumnInfo(name = "time_end") val timeEnd: Date?,
        val comment: String? = null,
        @ColumnInfo(name = "event_creator_id") val creatorID: String
)
