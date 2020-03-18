package com.example.student_attendance_ms.login

import android.content.Intent
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
import com.example.student_attendance_ms.main.MainActivity
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
    private lateinit var roles: RadioGroup
    private lateinit var signUpButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val signUpView = inflater.inflate(R.layout.sign_up_layout, container, false)
        onCreateAlreadyExistsButton(signUpView)

        loginField = signUpView.findViewById(R.id.loginTextField)
        passwordField = signUpView.findViewById(R.id.passwordTextField)
        signUpButton = signUpView.findViewById(R.id.signUpButton)
        roles = signUpView.findViewById(R.id.roles)

        return signUpView.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        // регистрация

        val userRole = onRolesRadioButtonClicked(view)

        signUpButton.setOnClickListener {
            Intent(context?.applicationContext, MainActivity::class.java).also {
                startActivity(it)
                activity?.finish()
            }
//            UserApi.retrofitService.createUser(
//                    loginField.toString(), passwordField.toString(), userRole
//            ).enqueue(object: Callback<String>{
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
//                }
//
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    Toast.makeText(context, response.body(), Toast.LENGTH_LONG).show()
//                }
//            })
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
            Toast.makeText(context, "${resources.getString(R.string.selected_role)}: ${role.text}", Toast.LENGTH_SHORT).show()
        }
        return roleUser
    }

}
