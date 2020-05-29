package com.example.student_attendance_ms.helper.validation

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class SignUpPattern{

    private val regexLogin: Pattern =
            Pattern.compile("^" +
                    "(?=.*[0-9])" + // минимум 1 цифра
                    "(?=.*[a-z])" + // минимум 1 строчная буква
                    "(?=\\S+$)" + // без пробелов
                    ".{4,}" + //минимум 8 символов
                    "$"
            )

    private val regexPassword: Pattern =
            Pattern.compile("^" +
                    "(?=.*[0-9])" + // минимум 1 цифра
                    "(?=.*[a-z])" + // минимум 1 строчная буква
                    "(?=.*[A-Z])" + // минимум 1 прописная буква
                    "(?=.*[!@#$%^&*_+=])" + // минимум 1 специальный символ
                    "(?=\\S+$)" + // без пробелов
                    ".{4,}" + //минимум 8 символов
                    "$"
            )

    fun isEmailValid(emailInputLayout: TextInputLayout): Boolean{
        val valid: Boolean
        val email = emailInputLayout.editText?.text.toString().trim()

        if (email.isEmpty()){
            emailInputLayout.error = "Поле не должно быть пустым"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInputLayout.error = "Некорректная почта"
            valid = false
        } else {
            emailInputLayout.error = null
            valid = true
        }

        return valid
    }

    fun isLoginValid(loginInputLayout: TextInputLayout): Boolean{
        val valid: Boolean
        val login = loginInputLayout.editText?.text.toString().trim()

        if (login.isEmpty()){
            loginInputLayout.error = "Поле не должно быть пустым"
            valid = false
        } else if (!regexLogin.matcher(login).matches()){
            loginInputLayout.error = "Некорректный логин"
            valid = false
        } else if (login.length > 15){
            loginInputLayout.error = "Слишком много символов"
            valid = false
        } else {
            loginInputLayout.error = null
            valid = true
        }

        return valid
    }

    fun isPasswordValid(passwordInputLayout: TextInputLayout): Boolean{
        val valid: Boolean
        val password = passwordInputLayout.editText?.text.toString().trim()

        if (password.isEmpty()){
            passwordInputLayout.error = "Поле не должно быть пустым"
            valid = false
        } else if (!regexPassword.matcher(password).matches()){
            passwordInputLayout.error = "Слабый пароль"
            valid = false
        } else {
            passwordInputLayout.error = null
            valid = true
        }

        return valid
    }
}