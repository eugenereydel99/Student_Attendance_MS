package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventMember (
        val memberImage: Int,
        @SerializedName(value = "id") val memberId: Int,
        @SerializedName(value = "first_name") val firstName: String,
        @SerializedName(value = "second_name") val secondName: String,
        @SerializedName(value = "last_name") val lastName: String
): Parcelable