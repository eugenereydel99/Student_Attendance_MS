package com.example.student_attendance_ms.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentProfileBinding
import com.example.student_attendance_ms.main.MainActivity
import com.example.student_attendance_ms.utils.SessionManager
import com.google.android.material.button.MaterialButton

class ProfileFragment : Fragment() {

    private lateinit var logoutButton: MaterialButton

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val intent = (activity as MainActivity).getAuthorizationData()

        val binding = DataBindingUtil.inflate<FragmentProfileBinding>(
                inflater,
                R.layout.fragment_profile,
                container,
                false
        )

        binding.logoutButton.setOnClickListener {
            val context = this.context
            if (context != null) {
                SessionManager(context).finishSession(context)
                this@ProfileFragment.activity?.finish()
            }
        }

        binding.user = intent

        return binding.root
    }


}
