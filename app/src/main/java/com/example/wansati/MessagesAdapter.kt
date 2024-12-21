package com.example.wansati

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wansati.R // Replace with your actual package name

data class Message(val userName: String, val preview: String)

class MessagesAdapter(
    private val messages: List<Message>,
    private val onItemClick: (Message) -> Unit
) : RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val messagePreviewTextView: TextView = itemView.findViewById(R.id.messagePreviewTextView)

        fun bind(message: Message) {
            userNameTextView.text = message.userName
            messagePreviewTextView.text = message.preview

            itemView.setOnClickListener {
                onItemClick(message)
            }
        }
    }
}
