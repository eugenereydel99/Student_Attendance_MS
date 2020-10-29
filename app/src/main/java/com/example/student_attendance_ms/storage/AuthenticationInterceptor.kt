package com.example.student_attendance_ms.storage

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor(
        private val sessionManager: SessionManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.header("No-Authentication") == null) {
            val accessToken = sessionManager.getAccessToken()
            request = request.newBuilder()
                    .addHeader("Authorization", accessToken)
                    .build()
        }
        return chain.proceed(request)
    }
}