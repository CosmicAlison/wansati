package com.example.wansati

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProfileAdapter(
    private val profiles: List<Profile>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_PROFILE = 1
        private const val VIEW_TYPE_PLACEHOLDER = 2
    }

    override fun getItemViewType(position: Int): Int {
        // Check if the list is empty; show placeholder
        return if (profiles.isEmpty()) VIEW_TYPE_PLACEHOLDER else VIEW_TYPE_PROFILE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_PROFILE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_card, parent, false)
            ProfileViewHolder(view)
        }else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.placeholder_item, parent, false)
            PlaceholderViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProfileViewHolder && profiles.isNotEmpty()) {
            val profile = profiles[position]
            holder.nameTextView.text = profile.name
            holder.detailsTextView.text = profile.bio

            // Load profile image (if available)
            Glide.with(holder.itemView.context)
                .load(profile.profilePictureUri)
                .into(holder.profileImageView)
        }
    }

    override fun getItemCount(): Int {
        return if (profiles.isEmpty()) 1 else profiles.size
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImageView: ImageView = itemView.findViewById(R.id.profileImage)
        val nameTextView: TextView = itemView.findViewById(R.id.profileName)
        val detailsTextView: TextView = itemView.findViewById(R.id.profileDetails)
    }

    class PlaceholderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeholderImageView: ImageView = itemView.findViewById(R.id.placeholderImage)
        val placeholderTextView: TextView = itemView.findViewById(R.id.placeholderText)
    }
}
