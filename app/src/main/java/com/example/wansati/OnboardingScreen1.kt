package com.example.wansati

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OnboardingScreen1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_screen_1)

        // Initialize views
        val logo: ImageView = findViewById(R.id.logo)
        val onboardingBackdrop: ImageView = findViewById(R.id.onboardingBackdrop)
        val onboardingImage: ImageView = findViewById(R.id.onboardingImage)
        val onboardingText: TextView = findViewById(R.id.onboardingText)
        val nextButton: Button = findViewById<Button>(R.id.nextButton)
        val skipButton: TextView = findViewById(R.id.skipText)
        val backButton: Button = findViewById<Button>(R.id.backButton)
        // Set up click listeners for the buttons
        backButton.setOnClickListener {
            // Navigate to the first onboarding screen
            val intent = Intent(this, OnboardingScreen::class.java)
            startActivity(intent)
            finish()
        }

        nextButton.setOnClickListener {
            // Navigate to the first onboarding screen
            val intent = Intent(this, OnboardingScreen2::class.java)
            startActivity(intent)
            finish()
        }

        skipButton.setOnClickListener {
            // Navigate directly to the authentication activity
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
