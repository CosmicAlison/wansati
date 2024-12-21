package com.example.wansati

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.wansati.R

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_authentication)
        var debug = true;

        val loginText = findViewById<TextView>(R.id.loginText)
        val signUpText = findViewById<TextView>(R.id.signUpText)

        val authenticateButton = findViewById<Button>(R.id.authenticateButton)

        authenticateButton.setOnClickListener{
            //To Do implement authentication logic with firebase
            if(debug) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        loginText.setOnClickListener {
            // Switch to Login form
        }

        signUpText.setOnClickListener {
            // Switch to Sign Up form
        }

        // Handle button clicks for social login
        val googleSignInButton = findViewById<Button>(R.id.googleSignIn)
        googleSignInButton.setOnClickListener {
            // To Do Google sign-in process
        }
        val facebookSignInButton = findViewById<Button>(R.id.facebookSignIn)
        facebookSignInButton.setOnClickListener {
            // To Do Google sign-in process
        }
    }
}
