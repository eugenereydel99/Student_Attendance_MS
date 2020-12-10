package com.example.student_attendance_ms.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

const val SIGN_IN_INDEX = 0
const val SIGN_UP_INDEX = 1

class LoginTabsAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){

    private val loginTabs: List<Fragment> = arrayListOf(
            SignInFragment(),
            SignUpFragment()
    )

    override fun getItemCount() = loginTabs.size

    override fun createFragment(position: Int) = loginTabs[position]

}