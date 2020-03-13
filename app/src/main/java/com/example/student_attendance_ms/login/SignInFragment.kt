package com.example.student_attendance_ms.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.student_attendance_ms.R
import kotlinx.android.synthetic.main.sign_in_layout.view.*

class SignInFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val signInView = inflater.inflate(R.layout.sign_in_layout, container, false)

        onCreateAccountButton(signInView)

        return signInView.rootView
    }


    private fun onCreateAccountButton(view: View){
        view.createAccountButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}
