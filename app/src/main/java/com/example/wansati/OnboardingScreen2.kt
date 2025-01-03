package com.example.wansati

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OnboardingScreen2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_screen_2)  // Use the appropriate layout

        val nextButton: Button = findViewById<Button>(R.id.nextButton)
        val skipButton: Button = findViewById<Button>(R.id.skipButton)

        nextButton.setOnClickListener {
              val intent = Intent(this, AuthenticationActivity::class.java).apply {
                putExtra("auth_mode", "log_in")
            }
            startActivity(intent)
            finish()
        }

        skipButton.setOnClickListener {
            // Navigate to AuthenticationActivity with "Sign Up" active
            val intent = Intent(this, AuthenticationActivity::class.java).apply {
                putExtra("auth_mode", "sign_up")
            }
            startActivity(intent)
            finish()
        }
    }
}
