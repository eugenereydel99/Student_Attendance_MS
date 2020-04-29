package com.example.student_attendance_ms.login

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.student_attendance_ms.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.about_app_dest -> {
//                val aboutAppFragment = AboutAppFragment()
//                supportFragmentManager.beginTransaction()
//                        .replace(R.id.loginNavHostFragment, aboutAppFragment)
//                        .addToBackStack(null)
//                        .commit()
//                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
