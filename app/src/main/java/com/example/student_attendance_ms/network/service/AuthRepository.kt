package com.example.student_attendance_ms.network.service

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.student_attendance_ms.utils.Constants

class AuthRepository (
        private val sharedPreferences: EncryptedSharedPreferences
){

    fun getToken(): String? {
        return sharedPreferences.getString(Constants.AUTH_TOKEN, null)
    }
}