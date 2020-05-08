package com.example.student_attendance_ms.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.EditText
import android.widget.Toast

import androidx.navigation.Navigation

import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.main.MainActivity
import com.example.student_attendance_ms.network.model.LoginResponse

import com.example.student_attendance_ms.network.model.UserCredentials
import com.example.student_attendance_ms.network.service.UserApiService
import com.example.student_attendance_ms.utils.SessionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {

    private lateinit var loginInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var signInButton: MaterialButton

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val signInView = inflater.inflate(R.layout.fragment_sign_in, container, false)

        initializeViews(signInView)
        onCreateAccount(signInView)

        return signInView.rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val sessionManager = SessionManager(context)

        signInButton = view.findViewById(R.id.signInButton)

        //авторизация
        signInButton.setOnClickListener {

            sessionManager.create("hello")
            Intent(context, MainActivity::class.java).also {
                startActivity(it)
                activity?.finish()
            }
//            val userCredentials = UserCredentials(
//                    loginEditText.text.toString(),
//                    passwordEditText.text.toString()
//            )
//
//            UserApiService.retrofitService.login(
//                    userCredentials
//            ).enqueue(object: Callback<LoginResponse> {
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
//                }
//
//                // обработка успешного запроса авторизации
//                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                    if (response.isSuccessful) {
//                        val token = response.body()?.token.toString()
//                        Toast.makeText(context, token, Toast.LENGTH_LONG).show()
//                        sessionManager.create(token)
//                        Intent(context, MainActivity::class.java).also {
//                            startActivity(it)
//                            activity?.finish()
//                        }
//
//
//                    } else {
//                        Toast.makeText(context, "Безуспешно", Toast.LENGTH_LONG).show()
//                    }
//                }
//            })
        }
    }

    private fun onCreateAccount(view: View){
        view.createAccountButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun initializeViews(view: View){

        // validation views
        loginInputLayout = view.findViewById(R.id.loginTextInput)
        passwordInputLayout = view.findViewById(R.id.passwordTextInput)
        loginEditText = view.findViewById(R.id.loginEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
    }
}
