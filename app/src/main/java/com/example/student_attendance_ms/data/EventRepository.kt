package com.example.student_attendance_ms.data

class EventRepository private constructor(private val eventDao: EventDao){

    fun getEvents() = eventDao.getEvents()

    fun getEvent(eventID: String) = eventDao.getEvent(eventID)

    companion object{

        // синглтон
        @Volatile private var instance: EventRepository? = null

        fun getInstance(eventDao: EventDao) =
                instance ?: synchronized(this){
                    instance ?: EventRepository(eventDao).also { instance = it }
                }
    }
}