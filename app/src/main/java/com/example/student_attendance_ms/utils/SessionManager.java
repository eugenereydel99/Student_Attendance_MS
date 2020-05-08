package com.example.student_attendance_ms.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.student_attendance_ms.login.LoginActivity;

public class SessionManager {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final Editor editor;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Const.LOGIN_SESSION, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void create(String authToken){
        editor.putString(Const.ACCESS_TOKEN, authToken);
        editor.putBoolean(Const.LOGIN_SESSION, true);
        editor.commit();
    }

    public void logout(){
        editor.remove(Const.LOGIN_SESSION).clear().commit();
        final Intent intent = new Intent(context, LoginActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public String getAuthToken(){
        return sharedPreferences.getString(Const.ACCESS_TOKEN, "token");
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(Const.LOGIN_SESSION, false);
    }

}
