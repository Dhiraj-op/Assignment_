package com.geeklord.jobsapp.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.geeklord.jobsapp.R
import com.geeklord.jobsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private lateinit var navController: NavController

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle window insets (for edge-to-edge display)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initNavHost()
        binding.setUpBottomNavigation()
    }

    companion object {
        // you can put any unique id here, but because I am using Navigation Component I prefer to put it as
        // the fragment id.
        val JOBS_ITEM = R.id.jobsFragment
        val SAVED_ITEM = R.id.savedJobsFragment
    }

    private fun initNavHost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun ActivityMainBinding.setUpBottomNavigation() {
        val bottomNavigationView = root.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.apply {
            setupWithNavController(navController)


            selectedItemId = JOBS_ITEM


            setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.jobsFragment -> {
                        navController.navigate(JOBS_ITEM)
                        true
                    }

                    R.id.savedJobsFragment -> {
                        navController.navigate(SAVED_ITEM)
                        true
                    }

                    else -> false
                }
            }
        }
    }

}