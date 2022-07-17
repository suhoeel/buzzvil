package com.buzzvil.android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.buzzvil.android.databinding.ActivityMainBinding
import com.buzzvil.android.main.MainFragment
import com.buzzvil.core.utils.NetworkManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

//    private lateinit var splashScreen: SplashScreen

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
//        splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        splashScreen.setKeepOnScreenCondition { true }

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            showSoftwareKeyboard(false)
            when (destination.id) {
                /*R.id.mainFragment -> {
                    binding.viewLoading.isVisible = false
                    binding.buttonBack.isVisible = false
                }*/
            }
        }

    }

    /*fun splashDone() {
        splashScreen.setKeepOnScreenCondition { false }
    }*/

    fun setLoadingProgress(isVisible: Boolean) {
        if (isVisible) {
            binding.viewLoading.text = "로딩중입니다."
        } else {
            binding.viewLoading.text = ""
        }

    }


}