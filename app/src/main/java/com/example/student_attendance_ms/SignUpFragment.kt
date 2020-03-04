package com.example.student_attendance_ms


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.sign_up_layout.view.*


class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val signUpView = inflater.inflate(R.layout.sign_up_layout, container, false)
        onCreateAlreadyExistsButton(signUpView)

        return signUpView.rootView
    }

    private fun onCreateAlreadyExistsButton(view: View){
        view.accountAlreadyExists.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

}
