package com.example.mypet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mypet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavControllerWithBottomNav()
        initDestination()

    }
    private fun initDestination() {
        navController.addOnDestinationChangedListener { navController, destination, arguments ->
//            if (destination.id != R.id.startFragment) {
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            }
            if (destination.id == R.id.registerFragment||
                destination.id == R.id.authFragment
//                destination.id == R.id.signUpFragment ||
//                destination.id == R.id.passwordRecoveryFragment ||
//                destination.id == R.id.enterTokenFragment ||
//                destination.id == R.id.createNewPasswordFragment
            ) {
                binding.navView.visibility = View.GONE
            } else {
                binding.navView.visibility = View.VISIBLE
            }
        }
    }
    private fun initNavControllerWithBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navView, navController)
    }
}