package com.example.student_attendance_ms.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.databinding.ActivityLoginBinding
import com.example.student_attendance_ms.main.MainActivity
import com.example.student_attendance_ms.storage.SessionManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var loginTabsAdapter: LoginTabsAdapter
    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // если сессия активна, то открывается основное окно приложения
        if (sessionManager.isLoggedIn()) {
            startActivity(Intent(
                    this,
                    MainActivity::class.java
            ))
            finish()
        }

        loginTabsAdapter = LoginTabsAdapter(this)
        binding.loginViewPager.adapter = loginTabsAdapter
        TabLayoutMediator(binding.loginTabs, binding.loginViewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            SIGN_IN_INDEX -> resources.getString(R.string.sign_in_title)
            SIGN_UP_INDEX -> resources.getString(R.string.sign_up_title)
            else -> null
        }
    }

}
