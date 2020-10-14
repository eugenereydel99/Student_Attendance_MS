package com.example.student_attendance_ms.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.utils.SignUpPattern
import com.example.student_attendance_ms.network.service.ApiService
import com.google.android.material.textfield.TextInputLayout
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {

    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var signUpButton: Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val signUpView = inflater.inflate(R.layout.fragment_sign_up, container, false)

        initializeViews(signUpView)

        return signUpView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        signUpButton = view.findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {

            if (SignUpPattern().isPasswordValid(passwordInputLayout)
                    and SignUpPattern().isEmailValid(emailInputLayout)){

                val userCredentials = AuthorizationRequest(
                        AuthData(
                                emailEditText.text.toString(),
                                passwordEditText.text.toString()
                        )
                )

                ApiService.build().createUser(
                        userCredentials
                ).enqueue(object: Callback<ResponseBody>{
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "Успешно", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Безуспешно", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }
    }

    private fun initializeViews(view: View){

        // validation views
        emailInputLayout = view.findViewById(R.id.emailSignUpTextField)
        passwordInputLayout = view.findViewById(R.id.passwordSignUpTextField)
        emailEditText = view.findViewById(R.id.emailSignUpEditText)
        passwordEditText = view.findViewById(R.id.passwordSignUpEditText)
    }

}
