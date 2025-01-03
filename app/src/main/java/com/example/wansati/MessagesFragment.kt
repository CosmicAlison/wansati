package com.example.wansati
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wansati.R // Replace with your actual package name
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MessagesFragment : Fragment() {

    private lateinit var messagesRecyclerView: RecyclerView
    private lateinit var newMessageFAB: FloatingActionButton
    private lateinit var messagesAdapter: MessagesAdapter

    // Sample data for messages
    private val messages = listOf(
        Message("John Doe", "Hey, how are you?"),
        Message("Jane Smith", "Let's catch up soon!"),
        Message("Alice Cooper", "Got your message!"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_messages, container, false)
        messagesRecyclerView = binding.findViewById(R.id.messagesRecyclerView)
        newMessageFAB = binding.findViewById(R.id.newMessageFAB)

        messagesRecyclerView.layoutManager = LinearLayoutManager(context)
        messagesAdapter = MessagesAdapter(messages) { message ->
            openChat(message)
        }
        messagesRecyclerView.adapter = messagesAdapter

        newMessageFAB.setOnClickListener {
            startNewMessage()
        }

        return binding
    }

    private fun openChat(message: Message) {
        Toast.makeText(context, "Opening chat with ${message.userName}", Toast.LENGTH_SHORT).show()
    }

    // Start a new message
    private fun startNewMessage() {
        Toast.makeText(context, "Starting a new message", Toast.LENGTH_SHORT).show()
    }
}
