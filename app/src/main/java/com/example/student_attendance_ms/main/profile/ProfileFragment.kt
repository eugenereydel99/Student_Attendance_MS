package com.example.student_attendance_ms.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.FragmentUserProfileBinding
import com.example.student_attendance_ms.main.MainActivity

class ProfileFragment : Fragment() {

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

        binding.user = intent

        return binding.root
    }

}
