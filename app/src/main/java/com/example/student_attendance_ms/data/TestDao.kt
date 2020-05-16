package com.example.student_attendance_ms.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TestDao {
    @Query("SELECT * FROM tests ORDER BY title")
    fun getTests(): LiveData<List<Test>>

    @Query("SELECT * FROM tests WHERE test_id = :testID")
    fun getTest(testID: String): LiveData<Test>
}