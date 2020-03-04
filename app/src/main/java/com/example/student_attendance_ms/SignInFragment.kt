package com.example.student_attendance_ms


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import kotlinx.android.synthetic.main.sign_in_layout.view.*
import kotlin.math.sign

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
