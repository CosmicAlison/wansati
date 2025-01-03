package com.example.wansati

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OnboardingScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_screen_0)

        val logo: ImageView = findViewById(R.id.logo)
        val onboardingBackdrop: ImageView = findViewById(R.id.onboardingBackdrop)
        val onboardingImage: ImageView = findViewById(R.id.onboardingImage)

        val onboardingText: TextView = findViewById(R.id.onboardingText)
        val nextButton: Button = findViewById<Button>(R.id.nextButton)
        val skipButton: Button = findViewById<Button>(R.id.skipButton)

        // Set up click listeners for the buttons
        nextButton.setOnClickListener {
            val intent = Intent(this, OnboardingScreen1::class.java)
            startActivity(intent)
            finish()
        }

        skipButton.setOnClickListener {
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
