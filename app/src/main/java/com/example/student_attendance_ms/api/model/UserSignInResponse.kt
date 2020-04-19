package com.example.student_attendance_ms.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserSignInResponse(
        @SerializedName("id")
        val id: Int,

        @SerializedName("role")
        val role: String,

        @SerializedName("authDate")
        val authDate: Date
)