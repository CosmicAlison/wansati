package com.example.wansati

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForumAdapter(private val posts: List<String>) : RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forum_post, parent, false)
        return ForumViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    class ForumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postText: TextView = itemView.findViewById(R.id.postText)

        fun bind(post: String) {
            postText.text = post
        }
    }
}
