package com.example.wansati

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {
    private lateinit var profileDatabase: ProfileDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val profileButton: ImageButton = view.findViewById(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(requireActivity(), ProfileActivity::class.java)
            startActivity(intent)
        }

        // Filter Button setup
        val filterButton: Button = view.findViewById(R.id.filterButton)
        filterButton.setOnClickListener {
        }

        // Mentor Carousel setup
        val mentorCarousel: RecyclerView = view.findViewById(R.id.mentorCarousel)
        setupMentorCarousel(mentorCarousel)

        val searchButton: ImageButton = view.findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            // Handle search button click, e.g., navigate to search page
        }

        return view
    }

    private fun setupMentorCarousel(recyclerView: RecyclerView) {
        // Fetch profiles from the database
        profileDatabase = ProfileDatabase.getInstance(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            val profiles = profileDatabase.profileDao().getAllProfiles()

            // On the main thread, set up the RecyclerView adapter
            launch(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = ProfileAdapter(profiles)
    }
}}}
