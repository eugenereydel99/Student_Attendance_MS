package com.example.student_attendance_ms.network.model

data class User (
        val userCredentials: UserCredentials
)

data class UserCredentials(
        val email: String,
        val password: String,
        val token: String? = null
)
