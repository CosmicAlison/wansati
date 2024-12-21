package com.example.wansati

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class SalaryCalculationFragment : Fragment() {

    private lateinit var jobTitleInput: EditText
    private lateinit var experienceInput: EditText
    private lateinit var educationSpinner: Spinner
    private lateinit var skillsInput: EditText
    private lateinit var predictButton: Button
    private lateinit var predictedSalaryText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.salary_calculation_fragment, container, false)

        // Initialize UI components
        jobTitleInput = view.findViewById(R.id.jobTitleInput)
        experienceInput = view.findViewById(R.id.experienceInput)
        educationSpinner = view.findViewById(R.id.educationSpinner)
        skillsInput = view.findViewById(R.id.skillsInput)
        predictButton = view.findViewById(R.id.predictButton)
        predictedSalaryText = view.findViewById(R.id.predictedSalaryText)

        // Setup education level dropdown
        val educationLevels = arrayOf(
            "High School",
            "Associate's Degree",
            "Bachelor's Degree",
            "Master's Degree",
            "Doctorate"
        )
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            educationLevels
        )
        educationSpinner.adapter = spinnerAdapter

        // Set up button click listener
        predictButton.setOnClickListener {
            predictSalary()
        }

        return view
    }

    private fun predictSalary() {
        // Collect inputs
        val jobTitle = jobTitleInput.text.toString()
        val experience = experienceInput.text.toString().toDoubleOrNull()
        val education = educationSpinner.selectedItem.toString()
        val skills = skillsInput.text.toString()

        // Validate inputs
        if (jobTitle.isBlank()) {
            Toast.makeText(requireContext(), "Please enter a job title", Toast.LENGTH_SHORT).show()
            return
        }
        if (experience == null || experience < 0) {
            Toast.makeText(requireContext(), "Please enter a valid number of years of experience", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulate salary prediction logic
        val predictedSalaryRange = simulateSalaryPrediction(
            jobTitle,
            experience,
            education,
            skills
        )

        // Display the result
        predictedSalaryText.text = "Predicted Salary Range: $predictedSalaryRange"
        predictedSalaryText.visibility = View.VISIBLE
    }

    /**
     * Simulate the salary prediction logic.
     * Replace this with a call to your actual algorithm or API.
     */
    private fun simulateSalaryPrediction(
        jobTitle: String,
        experience: Double,
        education: String,
        skills: String
    ): String {
        // Example logic for prediction
        val baseSalary = when (education) {
            "High School" -> 30000
            "Associate's Degree" -> 40000
            "Bachelor's Degree" -> 50000
            "Master's Degree" -> 60000
            "Doctorate" -> 70000
            else -> 30000
        }

        val experienceBonus = (experience * 2000).toInt()
        val skillsBonus = if (skills.isNotBlank()) 5000 else 0

        val lowerBound = baseSalary + experienceBonus + skillsBonus
        val upperBound = lowerBound + 10000

        return "$$lowerBound - $$upperBound"
    }
}
