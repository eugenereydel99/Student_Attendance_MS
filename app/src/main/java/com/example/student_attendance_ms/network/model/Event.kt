package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        val id: Int,
        val title: String,
        val location: String,
        val timeStart: String,
        val timeEnd: String,
        val creator: String,
        val comments: String,
        val date: String
//        @SerializedName("QR") val qr: String,
//        @SerializedName("created_at") val createdAt: String,
//        @SerializedName("updated_at") val updatedAt: String
): Parcelable

