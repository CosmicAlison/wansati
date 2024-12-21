package com.example.wansati

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wansati.ui.theme.WansatiTheme
import android.content.SharedPreferences
import android.os.Looper
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val apiKey = BuildConfig.PLACES_API_KEY
        if (!Places.isInitialized()) {
            // Log an error if apiKey is not set.
            if (apiKey.isEmpty() || apiKey == "DEFAULT_API_KEY") {
                Log.e("Places test", "No api key")
                finish()
                return
            }
            // Initialize the SDK
            Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)
            // Create a new PlacesClient instance
            val placesClient = Places.createClient(this)
        }
        // Install the system splash screen
        val splashScreen: SplashScreen = installSplashScreen()
           splashScreen.setKeepOnScreenCondition { false
        }

        Handler(Looper.getMainLooper()).postDelayed({
            checkAppState()
        }, 5000)
    }

    private fun checkAppState() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("WansatiPrefs", MODE_PRIVATE)

        // Check if it's the user's first time using the app
        val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)

        // Check if the user is authenticated
        val isAuthenticated = sharedPreferences.getBoolean("isAuthenticated", false)

        if (isFirstTime) {
            // If first time, navigate to OnboardingScreen0
            val intent = Intent(this, OnboardingScreen::class.java)
            startActivity(intent)
            finish()

            // Mark the app as not the first time anymore
            sharedPreferences.edit().putBoolean("isFirstTime", false).apply()
        } else if (isAuthenticated) {
            // If the user is authenticated, go to HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // If the user is not authenticated, navigate to AuthenticationActivity
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

