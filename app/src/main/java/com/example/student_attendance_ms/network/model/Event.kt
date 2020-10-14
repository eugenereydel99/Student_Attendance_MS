package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        val id: Int,
        val title: String,
        @SerializedName("QR") val qr: String,
        val location: String,
        val timeStart: String,
        val timeEnd: String,
        val creator: String,
        val comments: String,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("updated_at") val updatedAt: String
): Parcelable

