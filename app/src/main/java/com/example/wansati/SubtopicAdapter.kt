package com.example.wansati

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wansati.databinding.ItemSubtopicBinding

class SubtopicAdapter(private val subtopics: List<String>) : RecyclerView.Adapter<SubtopicAdapter.SubtopicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtopicViewHolder {
        val binding = ItemSubtopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubtopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubtopicViewHolder, position: Int) {
        holder.bind(subtopics[position])
    }

    override fun getItemCount(): Int = subtopics.size

    class SubtopicViewHolder(private val binding: ItemSubtopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subtopic: String) {
            binding.subtopicText.text = subtopic
        }
    }
}
