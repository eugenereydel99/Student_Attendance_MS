package com.example.student_attendance_ms.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.student_attendance_ms.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(
        @ApplicationContext private val context: Context
) : Storage {

    private val masterKey: MasterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()


    // Для хранения аутентификационных данных пользователя
    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            context, Constants.LOGIN_SESSION, masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String, value: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    override fun setBoolean(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    override fun getBoolean(key: String, value: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, value)
    }

    override fun deleteKey(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            clear()
            apply()
        }
    }

}