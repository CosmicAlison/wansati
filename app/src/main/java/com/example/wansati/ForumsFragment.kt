package com.example.wansati

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.wansati.databinding.FragmentForumBinding

class ForumsFragment : Fragment() {

    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set up RecyclerView
        val forumRecyclerView: RecyclerView = binding.forumRecyclerView
        forumRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        forumRecyclerView.adapter = ForumAdapter(getForumPosts())

        binding.addPostButton.setOnClickListener {
            // Handle new post creation
            createNewPost()
        }

        return view
    }

    private fun getForumPosts(): List<String> {
        // Fetch or create a list of forum posts
        return listOf("Welcome to the forum!", "Tips for getting started", "Ask questions here", "Share your experiences")
    }

    private fun createNewPost() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

