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
        @SerializedName("first_name") val firstName: String,
        @SerializedName("second_name") val secondName: String,
        @SerializedName("middle_name") val lastName: String
): Parcelable


