package com.example.student_attendance_ms.utils

import com.example.student_attendance_ms.database.UserDb
import com.example.student_attendance_ms.network.model.User

fun User.asDatabaseModel() = UserDb(
        id = id,
        firstName = firstName,
        secondName = secondName,
        lastName = lastName,
        email = email,
        createdAt = createdAt,
        updatedAt = updatedAt
)