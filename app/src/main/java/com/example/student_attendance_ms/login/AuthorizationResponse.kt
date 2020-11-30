package com.example.student_attendance_ms.login;

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorizationResponse(
        @SerializedName("accessToken") val accessToken: String,
//        @SerializedName("refreshToken") val refreshToken: String
) : Parcelable