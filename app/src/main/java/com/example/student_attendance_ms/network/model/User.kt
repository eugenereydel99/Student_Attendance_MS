package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class User(
        val id: String,
        val email: String,
        val group: String?,
        @SerializedName(value = "name") val name: String?,
        @SerializedName(value = "surname") val surname: String?,
        @SerializedName(value = "patronymic") val patronymic: String?
): Parcelable
