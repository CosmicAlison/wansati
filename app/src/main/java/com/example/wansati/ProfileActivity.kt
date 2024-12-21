package com.example.wansati

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.lifecycleScope
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.model.LatLng
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var profilePicture: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var bioEditText: EditText
    private lateinit var birthdayEditText: EditText
    private lateinit var currentJobTitleEditText: EditText
    private lateinit var currentJobDurationEditText: EditText
    private lateinit var industryEditText: EditText
    private lateinit var lastQualificationEditText: EditText
    private lateinit var locationButton: Button
    private lateinit var saveProfileButton: Button
    private lateinit var locationText: TextView
    private lateinit var addCertificationsButton: Button

    private val PICK_IMAGE_REQUEST = 1
    private val LOCATION_REQUEST_CODE = 2

    private var selectedImageUri: Uri? = null
    private var selectedLocation: LatLng? = null
    private lateinit var profileDatabase: ProfileDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        profileDatabase = Room.databaseBuilder(
            applicationContext,
            ProfileDatabase::class.java, "profile-database"
        ).build()

        setContentView(R.layout.fragment_profile)

        backButton = findViewById(R.id.backButton)
        profilePicture = findViewById(R.id.profilePicture)
        nameEditText = findViewById(R.id.nameEditText)
        bioEditText = findViewById(R.id.bioIntroEditText)
        birthdayEditText = findViewById(R.id.birthdayEditText)
        currentJobTitleEditText = findViewById(R.id.currentJobTitleEditText)
        currentJobDurationEditText = findViewById(R.id.currentJobDurationEditText)
        industryEditText = findViewById(R.id.industryEditText)
        lastQualificationEditText = findViewById(R.id.lastQualificationEditText)
        locationButton = findViewById(R.id.selectLocationButton)
        saveProfileButton = findViewById(R.id.saveProfileButton)
        locationText = findViewById(R.id.locationLabel)
        addCertificationsButton = findViewById(R.id.addCertificationsButton)

        val apiKey = BuildConfig.PLACES_API_KEY
        // Initialize Places API
        if (!Places.isInitialized()) {
            Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)
        }
        backButton.setOnClickListener { onBackPressed() }

        profilePicture.setOnClickListener { openProfilePicturePicker() }

        locationButton.setOnClickListener { openLocationPicker() }

        saveProfileButton.setOnClickListener { saveProfile() }
    }

    private fun openProfilePicturePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun openLocationPicker() {
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(this)
        startActivityForResult(intent, LOCATION_REQUEST_CODE)
    }

    private fun saveProfile() {
        val name = nameEditText.text.toString()
        val bio = bioEditText.text.toString()
        val birthday = birthdayEditText.text.toString()
        val location = locationText.text.toString()
        val profilePictureUri = selectedImageUri?.toString()
        val currentJobTitle = currentJobTitleEditText.text.toString()
        val currentJobDuration = currentJobDurationEditText.text.toString()
        val industry = industryEditText.text.toString()
        val lastQualification = lastQualificationEditText.text.toString()

        if (name.isEmpty() || bio.isEmpty() || birthday.isEmpty() || location.isEmpty() ||
            currentJobTitle.isEmpty() || currentJobDuration.isEmpty() || industry.isEmpty() || lastQualification.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), "Please fill in all fields", Snackbar.LENGTH_LONG).show()
            return
        }

        val profile = Profile(
            name = name,
            bio = bio,
            birthday = birthday,
            location = location,
            profilePictureUri = profilePictureUri,
            profession = currentJobTitle,
            education = lastQualification
        )

        lifecycleScope.launch {
            profileDatabase.profileDao().insertProfile(profile)
            Snackbar.make(findViewById(android.R.id.content), "Profile saved successfully!", Snackbar.LENGTH_SHORT).show()
        }
    }


    private fun createWorkExperienceView(): View {
        return TextView(this).apply {
            text = "New Work Experience Entry"
        }
    }

    private fun createCertificationView(): View {
        return TextView(this).apply {
            text = "New Certification Entry"
        }
    }

    private fun createEducationView(): View {
        return TextView(this).apply {
            text = "New Education Entry"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    selectedImageUri = data?.data
                    profilePicture.setImageURI(selectedImageUri)
                }
                LOCATION_REQUEST_CODE -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    selectedLocation = place.latLng
                    locationText.text = place.name
                }
            }
        } else {
            Log.e("ProfileActivity", "Activity result error: $resultCode")
        }
    }
}
