package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventMember (
        @SerializedName(value = "id") val memberId: Int,
        @SerializedName(value = "image") val memberImage: Int,
        @SerializedName(value = "name") val name: String,
        @SerializedName(value = "surname") val surname: String,
        @SerializedName(value = "patronymic") val patronymic: String,
        @SerializedName(value = "group") val group: String
): Parcelable