package com.example.student_attendance_ms.main.profile

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.main.MainActivity
import com.example.student_attendance_ms.utils.SessionManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var text: TextView
    private lateinit var button: Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val profileView = inflater.inflate(R.layout.fragment_user_profile, container, false)

        val sessionManager = SessionManager(context)

        text = profileView.findViewById(R.id.tokenProfile)
        button = profileView.findViewById(R.id.logoutButton)
        text.text = sessionManager.authToken
        button.setOnClickListener {
            sessionManager.logout()
        }

        return profileView.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

}
