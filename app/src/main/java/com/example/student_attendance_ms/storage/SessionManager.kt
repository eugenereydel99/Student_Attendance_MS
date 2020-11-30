package com.example.student_attendance_ms.storage

import android.content.Context
import android.content.Intent
import com.example.student_attendance_ms.login.AuthorizationResponse
import com.example.student_attendance_ms.login.LoginActivity
import com.example.student_attendance_ms.main.MainActivity
import com.example.student_attendance_ms.utils.Constants
import javax.inject.Inject

class SessionManager @Inject constructor(
        private val storage: IStorage
) {

    fun createSession(context: Context, response: AuthorizationResponse): Intent {

        val intent = Intent(context, MainActivity::class.java)
                .putExtra(Constants.AUTHORIZATION_DATA, response)

        storage.setString(Constants.AUTH_TOKEN, response.accessToken)
        storage.setBoolean(Constants.LOGGED_IN, true)

        return intent
    }

    fun finishSession(context: Context) {
        with(storage) {
            deleteKey(Constants.AUTH_TOKEN)
            deleteKey(Constants.LOGGED_IN)
        }

        context.startActivity(
                Intent(context, LoginActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    fun getAccessToken() = storage.getString(Constants.AUTH_TOKEN, "")

    fun isLoggedIn() = storage.getBoolean(Constants.LOGGED_IN, false)
}