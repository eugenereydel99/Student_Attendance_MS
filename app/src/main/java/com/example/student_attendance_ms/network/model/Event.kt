package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        val id: Int,
        val title: String,
        @SerializedName("type_of_event")  val type: String,
        val location: String,
        val timeStart: String,
        val timeEnd: String,
        val creator: String,
        val comments: String?,
        val date: String
): Parcelable

