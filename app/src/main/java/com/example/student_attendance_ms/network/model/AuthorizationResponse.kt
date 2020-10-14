package com.example.student_attendance_ms.network.model;

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorizationResponse(
        val id: Int,
        val email: String,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("second_name") val secondName: String,
        @SerializedName("last_name") val lastName: String,
        @SerializedName("authentication_token") val authToken: String
) : Parcelable