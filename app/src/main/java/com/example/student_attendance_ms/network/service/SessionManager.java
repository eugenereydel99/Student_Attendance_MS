package com.example.student_attendance_ms.network.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.student_attendance_ms.login.LoginActivity;
import com.example.student_attendance_ms.main.MainActivity;
import com.example.student_attendance_ms.network.model.AuthorizationResponse;
import com.example.student_attendance_ms.utils.Constants;

public class SessionManager {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.LOGIN_SESSION, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public Intent create(AuthorizationResponse userData){

        String authToken = userData.getAuthentication_token();
        Intent intent = new Intent(context, MainActivity.class);

        intent.putExtra(Constants.AUTHORIZATION_DATA, userData);
        editor.putString(Constants.ACCESS_TOKEN, authToken);
        editor.putBoolean(Constants.LOGIN_SESSION, true);
        editor.commit();

        return intent;
    }

    public void logout(){
        editor.remove(Constants.LOGIN_SESSION).clear().commit();
        final Intent intent = new Intent(context, LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(Constants.LOGIN_SESSION, false);
    }

}
