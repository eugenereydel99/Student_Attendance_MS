package com.example.student_attendance_ms.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isEmpty
import androidx.navigation.Navigation
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.login.api.UserApi
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.sign_up_layout.*
import kotlinx.android.synthetic.main.sign_up_layout.view.*
import kotlinx.android.synthetic.main.sign_up_layout.view.loginTextField
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {

    private lateinit var loginField: TextInputLayout
    private lateinit var passwordField: TextInputLayout
    private lateinit var repeatPasswordField: TextInputLayout
    private lateinit var roles: RadioGroup
    private lateinit var signUpButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val signUpView = inflater.inflate(R.layout.sign_up_layout, container, false)
        onCreateAlreadyExistsButton(signUpView)

        loginField = signUpView.findViewById(R.id.loginTextField)
        passwordField = signUpView.findViewById(R.id.passwordTextField)
        repeatPasswordField = signUpView.findViewById(R.id.repeatPasswordTextField)
        signUpButton = signUpView.findViewById(R.id.signUpButton)
        roles = signUpView.findViewById(R.id.roles)

        return signUpView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        // регистрация
        signUpButton.setOnClickListener {
            val userRole = onRolesRadioButtonClicked(view)
            UserApi.retrofitService.createUser(
                    loginField.toString(), repeatPasswordField.toString(), userRole
            ).enqueue(object: Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(context, response.body(), Toast.LENGTH_LONG).show()
                }
            })
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

    private fun onRolesRadioButtonClicked(view: View): String{
        var roleUser: String = ""
        roles.setOnCheckedChangeListener { _, checkedId ->
            val role: RadioButton = view.findViewById(checkedId)
            roleUser = role.text.toString()
            Toast.makeText(context, "Selected role: ${role.text}", Toast.LENGTH_LONG).show()
        }
        return roleUser
    }

}
