package com.example.student_attendance_ms.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.student_attendance_ms.databinding.FragmentSignInBinding
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.storage.SessionManager
import dagger.hilt.android.AndroidEntryPoint

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val scope = requireContext()

        //авторизация
        binding.signInButton.setOnClickListener {
            val userCredentials = AuthData(
                    binding.emailSignInEditText.text.toString(),
                    binding.passwordSignInEditText.text.toString()
            )

            apiService.login(
                    userCredentials
            ).enqueue(object : Callback<AuthorizationResponse> {
                override fun onFailure(call: Call<AuthorizationResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                // обработка успешного запроса авторизации
                override fun onResponse(call: Call<AuthorizationResponse>, response: Response<AuthorizationResponse>) {
                    if (response.isSuccessful) {
                        val authResponse = response.body()
                        if (authResponse != null) {
                            sessionManager.createSession(scope, authResponse).also {
                                startActivity(it)
                                this@SignInFragment.activity?.finish()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Безуспешно", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}
