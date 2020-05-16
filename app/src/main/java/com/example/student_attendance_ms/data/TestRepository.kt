package com.example.student_attendance_ms.data

class TestRepository private constructor(private val testDao: TestDao){

    fun getTests() = testDao.getTests()

    fun getTest(testID: String) = testDao.getTest(testID)

    companion object {

        // синглтон
        @Volatile private var instance: TestRepository? = null

        fun getInstance(testDao: TestDao) =
                instance ?: synchronized(this) {
                    instance ?: TestRepository(testDao).also { instance = it }
                }
    }
}