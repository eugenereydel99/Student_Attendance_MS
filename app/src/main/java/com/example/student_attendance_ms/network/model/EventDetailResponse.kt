package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventDetailResponse(
        @SerializedName("participants") val eventMembers: List<EventMember>,
        val isSubscribed: Boolean
): Parcelable
