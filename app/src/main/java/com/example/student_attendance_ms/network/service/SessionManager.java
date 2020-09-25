package com.example.student_attendance_ms.network.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.student_attendance_ms.login.LoginActivity;
import com.example.student_attendance_ms.main.MainActivity;
import com.example.student_attendance_ms.network.model.AuthorizationResponse;
import com.example.student_attendance_ms.utils.Constants;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SessionManager {

    private final Context context;
    private Editor editor;
    private final SharedPreferences sessionPrefs;

    public SessionManager(Context context) throws GeneralSecurityException, IOException {
        this.context = context;

        MasterKey masterKey = new MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        sessionPrefs = EncryptedSharedPreferences.create(
                context, "session", masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }

    public Intent createSession(AuthorizationResponse authResponse){

        String authToken = authResponse.getAuthToken();
        Intent intent = new Intent(context, MainActivity.class);

        intent.putExtra(Constants.AUTHORIZATION_DATA, authResponse);

        editor = sessionPrefs.edit();
        editor.putString(Constants.AUTH_TOKEN, authToken);
        editor.putBoolean(Constants.LOGIN_SESSION, true);
        editor.apply();

        return intent;
    }

    public void finishSession(){
        editor.remove(Constants.AUTH_TOKEN).clear().apply();
        editor.remove(Constants.LOGIN_SESSION).clear().apply();
        final Intent intent = new Intent(context, LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isLoggedIn(){
        return sessionPrefs.getBoolean(Constants.LOGIN_SESSION, false);
    }

}
