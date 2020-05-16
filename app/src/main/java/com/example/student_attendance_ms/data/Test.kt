package com.example.student_attendance_ms.data

import androidx.room.*

@Entity(tableName = "tests")
data class Test(
        @PrimaryKey @ColumnInfo(name = "test_id") val testID: String,
        val title: String,
        @ColumnInfo(name = "time_limit") val timeLimit: Int,
        @ColumnInfo(name = "creator_id") val creatorID: String
)