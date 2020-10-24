package com.example.student_attendance_ms.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences
import android.content.SharedPreferences.*
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.example.student_attendance_ms.login.LoginActivity;
import com.example.student_attendance_ms.main.MainActivity;
import com.example.student_attendance_ms.login.AuthorizationResponse;

class SessionManager(val context: Context) {

    private val masterKey: MasterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    private val sessionPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            context, Constants.LOGIN_SESSION, masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private var editor: Editor = sessionPreferences.edit()

    fun createSession(authorizationResponse: AuthorizationResponse): Intent {

        val intent = Intent(context, MainActivity::class.java)
                .putExtra(Constants.AUTHORIZATION_DATA, authorizationResponse)

        editor.putInt(Constants.USER_ID, authorizationResponse.id)
        editor.putString(Constants.AUTH_TOKEN, authorizationResponse.authToken)
        editor.putBoolean(Constants.LOGGED_IN, true)
        editor.apply()

        return intent
    }

    fun finishSession(context: Context) {
        editor.remove(Constants.USER_ID).clear().apply()
        editor.remove(Constants.AUTH_TOKEN).clear().apply()
        editor.remove(Constants.LOGGED_IN).clear().apply()

        context.startActivity(
                Intent(context, LoginActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun getUserId(): Int {
        return sessionPreferences.getInt(Constants.USER_ID, 0)
    }

    fun getToken(): String? {
        return sessionPreferences.getString(Constants.AUTH_TOKEN, null)
    }

    fun isLoggedIn(): Boolean {
        return sessionPreferences.getBoolean(Constants.LOGGED_IN, false)
    }
}