package com.example.student_attendance_ms.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

abstract class AbstractEventMember{
    abstract val memberId: Int
    abstract val memberImage: Int
    abstract val name: String
    abstract val surname: String
    abstract val patronymic: String
    abstract val group: String
}

@Parcelize
data class EventMember (
        @SerializedName(value = "id") override val memberId: Int,
        @SerializedName(value = "image") override val memberImage: Int,
        @SerializedName(value = "name") override val name: String,
        @SerializedName(value = "surname") override val surname: String,
        @SerializedName(value = "patronymic") override val patronymic: String,
        @SerializedName(value = "group") override val group: String
): Parcelable, AbstractEventMember()

@Parcelize
data class EventVisitor (
        @SerializedName(value = "id") override val memberId: Int,
        @SerializedName(value = "image") override val memberImage: Int,
        @SerializedName(value = "name") override val name: String,
        @SerializedName(value = "surname") override val surname: String,
        @SerializedName(value = "patronymic") override val patronymic: String,
        @SerializedName(value = "group") override val group: String,
        val presence: Boolean
): Parcelable, AbstractEventMember()