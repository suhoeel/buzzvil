package com.buzzvil.campaign

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.buzzvil.campaign.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var splashScreen: SplashScreen

    lateinit var binding: ActivityMainBinding
    lateinit var navHostFragment: NavHostFragment

    private fun showSoftwareKeyboard(showKeyboard: Boolean) {
        currentFocus?.let {
            val inputManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                it.windowToken,
                if (showKeyboard) InputMethodManager.SHOW_FORCED else InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition { true }

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            showSoftwareKeyboard(false)
            when (destination.id) {
                R.id.mainFragment -> {
                    binding.viewLoading.isVisible = false
                    binding.buttonBack.isVisible = false
                }
            }
        }

    }

    fun splashDone() {
        splashScreen.setKeepOnScreenCondition { false }
    }

    fun setLoadingProgress(isVisible: Boolean) {
        binding.viewLoading.isVisible = isVisible
        binding.viewLoading.setOnClickListener {  }
    }


}