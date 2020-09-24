package com.example.student_attendance_ms.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentUserProfileBinding
import com.example.student_attendance_ms.main.MainActivity
import com.example.student_attendance_ms.network.service.SessionManager
import com.google.android.material.button.MaterialButton

class ProfileFragment : Fragment() {

    private lateinit var logoutButton: MaterialButton

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val intent = (activity as MainActivity).getAuthorizationData()

        val binding = DataBindingUtil.inflate<FragmentUserProfileBinding>(
                inflater,
                R.layout.fragment_user_profile,
                container,
                false
        )

        binding.logoutButton.setOnClickListener {
            SessionManager(context).finishSession()
        }

        binding.user = intent

        return binding.root
    }


}
