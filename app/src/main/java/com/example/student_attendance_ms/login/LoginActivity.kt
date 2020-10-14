package com.example.student_attendance_ms.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.student_attendance_ms.R
import com.example.student_attendance_ms.main.MainActivity
import com.example.student_attendance_ms.utils.SessionManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LoginActivity : AppCompatActivity() {

    private lateinit var loginTabsAdapter: LoginTabsAdapter
    private lateinit var loginViewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // если сессия активна, то открывается основное окно приложения
        if (SessionManager(this).isLoggedIn()) {
            startActivity(Intent(
                    this,
                    MainActivity::class.java
            ))
            finish()
        }

        setContentView(R.layout.activity_login)

        loginTabsAdapter = LoginTabsAdapter(this)
        loginViewPager = findViewById(R.id.login_ViewPager)
        loginViewPager.adapter = loginTabsAdapter
        tabLayout = findViewById(R.id.loginTabs)
        TabLayoutMediator(tabLayout, loginViewPager) { tab, position ->
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
