package com.example.student_attendance_ms.login.api.model

import com.google.gson.annotations.SerializedName

data class UserSignUpResponse(
    @SerializedName("message")
    val message: String,

    @SerializedName("error")
    val error: Boolean
)