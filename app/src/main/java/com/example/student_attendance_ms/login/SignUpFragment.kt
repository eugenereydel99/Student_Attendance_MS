package com.example.student_attendance_ms.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.helper.SignUpPattern
import com.example.student_attendance_ms.login.api.model.UserSignUpResponse
import com.example.student_attendance_ms.login.api.service.UserApi
import com.example.student_attendance_ms.main.MainActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.sign_up_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {

    private lateinit var loginInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var roles: RadioGroup
    private lateinit var signUpButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val signUpView = inflater.inflate(R.layout.sign_up_layout, container, false)
        onCreateAlreadyExistsButton(signUpView)

        initializeViews(signUpView)
        roles = signUpView.findViewById(R.id.roles)

        return signUpView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // регистрация
        val userRole = onRolesRadioButtonClicked(view)

        signUpButton = view.findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {

            if (SignUpPattern().isLoginValid(loginInputLayout) and SignUpPattern().isPasswordValid(passwordInputLayout)){
                UserApi.retrofitService.createUser(
                        loginEditText.toString(), passwordEditText.toString(), userRole
                ).enqueue(object: Callback<UserSignUpResponse>{
                    override fun onFailure(call: Call<UserSignUpResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<UserSignUpResponse>, response: Response<UserSignUpResponse>) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show()
                            Intent(context?.applicationContext, MainActivity::class.java).also {
                                startActivity(it)
                                activity?.finish()
                            }
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

    private fun onRolesRadioButtonClicked(view: View): String{
        var roleUser = ""
        roles.setOnCheckedChangeListener { _, checkedId ->
            val role: RadioButton = view.findViewById(checkedId)
            roleUser = role.text.toString()
            Toast.makeText(context, "${resources.getString(R.string.selected_role)}: ${role.text}", Toast.LENGTH_SHORT).show()
        }
        return roleUser
    }

    private fun initializeViews(view: View){

        // validation views
        loginInputLayout = view.findViewById(R.id.loginTextInput)
        passwordInputLayout = view.findViewById(R.id.passwordTextInput)
        loginEditText = view.findViewById(R.id.loginEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
    }

}
