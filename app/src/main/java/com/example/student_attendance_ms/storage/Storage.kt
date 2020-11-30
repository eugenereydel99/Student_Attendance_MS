package com.example.student_attendance_ms.storage

interface IStorage {
    fun setString(key: String, value: String)
    fun getString(key: String, value: String): String
    fun setBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, value: Boolean): Boolean
    fun deleteKey(key: String)
}