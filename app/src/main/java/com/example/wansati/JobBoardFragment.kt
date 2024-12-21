package com.example.wansati

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wansati.databinding.FragmentJobBoardBinding
import com.example.wansati.databinding.ItemJobBinding

class JobBoardFragment : Fragment() {

    private lateinit var binding: FragmentJobBoardBinding
    private lateinit var jobAdapter: JobAdapter
    private lateinit var searchEditText: EditText
    private lateinit var jobsRecyclerView: RecyclerView
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    // Sample job list for demonstration
    private val jobList = listOf(
        Job("Software Developer", "Tech Company", "New York", 4.5, "Help build innovative tech solutions"),
        Job("Data Analyst", "Finance Corp", "San Francisco", 4.2, "Analyze financial trends"),
        Job("Project Manager", "Marketing Co.", "Los Angeles", 4.8, "Lead projects to success")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize binding
        binding = FragmentJobBoardBinding.inflate(inflater, container, false)

        // Initialize UI components
        searchEditText = binding.searchEditText
        jobsRecyclerView = binding.jobBoardRecyclerView
        imageView = binding.topImage
        textView = binding.ratingPromptText

        // Setup the RecyclerView
        jobAdapter = JobAdapter(jobList)
        jobsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        jobsRecyclerView.adapter = jobAdapter

        // Set up the search functionality
        searchEditText.setOnEditorActionListener { _, _, _ ->
            filterJobs(searchEditText.text.toString())
            true
        }

        // Handle the "Help other women" section
        //imageView.setImageResource(R.drawable.ic_help)
        textView.text = "Help other women in your area by rating your current workplace"

        return binding.root
    }

    // Function to filter jobs based on search query
    private fun filterJobs(query: String) {
        val filteredJobs = jobList.filter { job ->
            job.title.contains(query, ignoreCase = true) ||
                    job.company.contains(query, ignoreCase = true)
        }
        jobAdapter.submitList(filteredJobs)
    }
}

data class Job(
    val title: String,
    val company: String,
    val location: String,
    val rating: Double,
    val description: String
)

class JobAdapter(private var jobs: List<Job>) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(jobs[position])
    }

    override fun getItemCount(): Int = jobs.size

    fun submitList(newJobs: List<Job>) {
        jobs = newJobs
        notifyDataSetChanged()
    }

    class JobViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(job: Job) {
            binding.jobTitle.text = job.title
            binding.companyName.text = job.company
            binding.jobLocation.text = job.location
            binding.jobRating.text = job.rating.toString()
            binding.jobDescription.text = job.description
        }
    }
}

