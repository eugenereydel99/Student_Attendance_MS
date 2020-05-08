package com.example.student_attendance_ms.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("authentication_token")
        val token: String
)