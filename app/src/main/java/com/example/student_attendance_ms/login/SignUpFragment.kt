package com.example.student_attendance_ms.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.api.service.User
import com.example.student_attendance_ms.helper.validation.SignUpPattern
import com.example.student_attendance_ms.api.service.UserApiTest
import com.example.student_attendance_ms.api.service.UserX
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
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
        onCreateAlreadyExistsButton(signUpView)

        initializeViews(signUpView)

        return signUpView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        signUpButton = view.findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {

            if (SignUpPattern().isPasswordValid(passwordInputLayout)
                    and SignUpPattern().isEmailValid(emailInputLayout)){

                val userCredentials = UserX(
                        User(
                                emailEditText.text.toString(),
                                passwordEditText.text.toString()
                        )
                )

                UserApiTest.retrofitService.createUser(
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

    // сохранение состояния фрагмента
    override fun onSaveInstanceState(outState: Bundle) {

    }

    // получение ранее сохраненного состояния
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    private fun onCreateAlreadyExistsButton(view: View){
        view.accountAlreadyExists.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun initializeViews(view: View){

        // validation views
        emailInputLayout = view.findViewById(R.id.emailTextInput)
        passwordInputLayout = view.findViewById(R.id.passwordTextInput)
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
    }

}
