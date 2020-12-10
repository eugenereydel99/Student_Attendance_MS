package com.example.student_attendance_ms.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.database.UserDao
import com.example.student_attendance_ms.databinding.FragmentUserProfileBinding
import com.example.student_attendance_ms.storage.SessionManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private val viewModel: UserProfileViewModel by viewModels()
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var userDao: UserDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userParams.observe(viewLifecycleOwner) {
            binding.user = it
        }

        binding.logoutButton.setOnClickListener {
            val context = this.context
            MaterialAlertDialogBuilder(it.context, R.style.ProfileDialog)
                    .setMessage(resources.getString(R.string.supporting_text))
                    .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    }
                    .setPositiveButton(resources.getString(R.string.logout_title)) { dialog, which ->
                        if (context != null) {
                            sessionManager.finishSession(context)
                            lifecycleScope.launch {
                                userDao.deleteUser()
                            }
                            this@UserProfileFragment.activity?.finish()
                        }
                    }.apply {
                        show()
                    }
        }

    }
}