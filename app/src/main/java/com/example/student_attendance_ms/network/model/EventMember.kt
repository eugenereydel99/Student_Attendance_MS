package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventMember (
        @SerializedName(value = "id") val memberId: Int,
        @SerializedName(value = "image") val memberImage: Int,
        @SerializedName(value = "first_name") val firstName: String,
        @SerializedName(value = "second_name") val secondName: String,
        @SerializedName(value = "middle_name") val lastName: String,
        @SerializedName(value = "group") val group: String
): Parcelable