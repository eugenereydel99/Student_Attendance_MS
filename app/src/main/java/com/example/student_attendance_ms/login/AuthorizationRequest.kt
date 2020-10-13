package com.example.student_attendance_ms.login

data class UserX(
     val user: User
)

data class User(
        val email: String,
        val password: String
)