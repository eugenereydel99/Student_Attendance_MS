package com.example.student_attendance_ms.login;

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorizationResponse(
        val id: Int,
        @SerializedName("authentication_token") val authToken: String
) : Parcelable