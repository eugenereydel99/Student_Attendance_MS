package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val id: String,
        val email: String,
        @SerializedName(value = "first_name") val firstName: String,
        @SerializedName(value = "second_name") val secondName: String,
        @SerializedName(value = "last_name") val lastName: String,
        @SerializedName(value = "created_at") val createdAt: String,
        @SerializedName(value = "updated_at") val updatedAt: String,
) : Parcelable