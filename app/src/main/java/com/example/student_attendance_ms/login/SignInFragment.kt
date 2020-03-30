package com.example.student_attendance_ms.login


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.EditText

import androidx.navigation.Navigation

import com.example.student_attendance_ms.R

import com.example.student_attendance_ms.main.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

import kotlinx.android.synthetic.main.sign_in_layout.view.*

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

        val signInView = inflater.inflate(R.layout.sign_in_layout, container, false)

        initializeViews(signInView)
        onCreateAccount(signInView)

        return signInView.rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        signInButton = view.findViewById(R.id.signInButton)
        signInButton.setOnClickListener {
            Intent(context?.applicationContext, MainActivity::class.java).also {
                startActivity(it)
                activity?.finish()
            }
        }


        //авторизация
//        signInButton.setOnClickListener {
//            UserApi.retrofitService.login(
//                    loginEditText.toString(),
//                    passwordEditText.toString()
//            ).enqueue(object: Callback<String> {
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(context, response.body(), Toast.LENGTH_LONG).show()
//                        Intent(context?.applicationContext, MainActivity::class.java).also {
//                            startActivity(it)
//                            activity?.finish()
//                        }
//                    }
//                }
//
//            })
//
//        }
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
