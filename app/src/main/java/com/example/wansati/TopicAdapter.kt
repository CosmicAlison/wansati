package com.example.wansati

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wansati.databinding.ItemTopicBinding

class TopicAdapter : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    private var topics = listOf<Topic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(topics[position])
    }

    override fun getItemCount(): Int = topics.size

    fun submitList(newTopics: List<Topic>) {
        topics = newTopics
        notifyDataSetChanged()
    }

    class TopicViewHolder(private val binding: ItemTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(topic: Topic) {
            binding.topicTitle.text = topic.name
            binding.subtopicsRecyclerView.adapter = SubtopicAdapter(topic.subtopics)
        }
    }
}
