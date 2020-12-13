package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
        val id: Int,
        @SerializedName("name") val title: String,
        @SerializedName("type_event")  val eventType: String,
        val location: String,
        val comments: String?,
        val date: String,
        @SerializedName("time_start") val timeStart: String,
        @SerializedName("time_end")  val timeEnd: String,
        @SerializedName("check_type") val checkType: String,
        val creator: Creator
): Parcelable

@Parcelize
data class Creator(
        @SerializedName(value = "name") val name: String,
        @SerializedName(value = "surname") val surname: String,
        @SerializedName(value = "patronymic") val patronymic: String
): Parcelable

@Parcelize
data class AttendanceResult(
        @SerializedName(value = "message") val message: String
): Parcelable

