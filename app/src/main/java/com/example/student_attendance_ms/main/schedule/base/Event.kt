package com.example.student_attendance_ms.main.schedule.base

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val title: String,
    @Json(name = "QR") val qr: String,
    val location: String,
    val timeStart: String,
    val timeEnd: String,
    val creator: String,
    val comments: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
): Parcelable