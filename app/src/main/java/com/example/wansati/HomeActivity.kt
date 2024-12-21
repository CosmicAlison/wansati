package com.example.wansati

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import com.example.wansati.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment
        loadFragment(HomeFragment())
        binding.bottomNavigationBar.selectedItemId = R.id.navigation_home
        binding.bottomNavigationBar.itemIconTintList = null;
        binding.bottomNavigationBar.itemTextColor = ContextCompat.getColorStateList(this, R.color.dark_grey);
        //var homeButton = binding.bottomNavigationBar.findViewById<View>(R.id.navigation_home)
        //homeButton.setBackgroundResource(R.drawable.square_rounded_button_fill)
        //val largeLabel = homeButton

        // Handle navigation item selection
        binding.bottomNavigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                }
                R.id.navigation_job_board -> {
                    loadFragment(JobBoardFragment())
                    changeIconTint(R.id.navigation_job_board)}
                R.id.navigation_salary_calculation ->
                    {
                        loadFragment(SalaryCalculationFragment())
                        changeIconTint(R.id.navigation_salary_calculation)
                    }
                R.id.navigation_forum -> {
                    loadFragment(ForumsFragment())
                    changeIconTint(R.id.navigation_forum)}
                R.id.navigation_messages -> {
                    loadFragment(MessagesFragment())
                    changeIconTint(R.id.navigation_messages)
                }
                else -> false
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }

    // Function to change the tint of the icons programmatically
    private fun changeIconTint(itemId: Int) {
        val bottomNavigation = binding.bottomNavigationBar
        // Apply the tint to the clicked item
        val selectedItem = bottomNavigation.menu.findItem(itemId)
        selectedItem.icon?.setTintList(ContextCompat.getColorStateList(this, R.color.dark_grey))
    }
}
