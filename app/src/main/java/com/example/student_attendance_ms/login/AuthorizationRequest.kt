package com.example.student_attendance_ms.login

data class AuthorizationRequest(
     val user: AuthData
)

data class AuthData(
        val email: String,
        val password: String
)