package com.example.student_attendance_ms.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.student_attendance_ms.databinding.FragmentSignUpBinding
import com.example.student_attendance_ms.network.service.ApiService
import com.example.student_attendance_ms.utils.SignUpPattern
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var apiService: ApiService

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.signUpButton.setOnClickListener {

            if (SignUpPattern().isPasswordValid(binding.passwordSignUpTextField)
                    and SignUpPattern().isEmailValid(binding.emailSignUpTextField)) {

                val userCredentials = AuthData(
                                binding.emailSignUpEditText.text.toString(),
                                binding.passwordSignUpEditText.text.toString()
                )


                apiService.createUser(
                        userCredentials
                ).enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "Успешно", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Безуспешно", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }
    }
}
