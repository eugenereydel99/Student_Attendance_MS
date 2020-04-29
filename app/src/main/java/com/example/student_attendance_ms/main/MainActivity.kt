package com.example.student_attendance_ms.main

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.student_attendance_ms.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_user_profile.*

private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.INTERNET
)

class MainActivity : AppCompatActivity() {

//    private lateinit var navController: NavController
//    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)

//        bottomNavigationView = findViewById(R.id.bottom_nav_view)
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        bottomNavigationView.setupWithNavController(navController)
//        NavigationUI.setupActionBarWithNavController(this, navController)
//
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            when (destination.id){
//                R.id.attendanceEntryFragment -> {
//                    bottomNavigationView.visibility = View.GONE
//                }
//                else -> bottomNavigationView.visibility = View.VISIBLE
//            }
//        }
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // устанавливаем контроллер в хост
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        // установка конфигурации панели инструментов
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
                setOf(R.id.profileFragment, R.id.attendanceFragment, R.id.scheduleFragment),
                drawerLayout
        )

        setupActionBar(navController, appBarConfiguration)
        setupNavigationMenu(navController)
        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id){
                R.id.attendanceEntryFragment -> {
                    bottom_nav_view?.visibility = View.GONE
                }
                else -> bottom_nav_view?.visibility = View.VISIBLE
            }
        }

    }

    private fun setupActionBar(
            navController: NavController,
            appBarConfig: AppBarConfiguration
    ){
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupNavigationMenu(navController: NavController){
        findViewById<NavigationView>(R.id.nav_view)
                ?.setupWithNavController(navController)
    }

    private fun setupBottomNavMenu(navController: NavController){
        findViewById<BottomNavigationView>(R.id.bottom_nav_view)
                ?.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val retValue =  super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView == null){
            menuInflater.inflate(R.menu.overflow_menu, menu)
            return true
        }
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

}
