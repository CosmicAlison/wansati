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
        // Add more messages here
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_messages, container, false)

        // Initialize views
        messagesRecyclerView = binding.findViewById(R.id.messagesRecyclerView)
        newMessageFAB = binding.findViewById(R.id.newMessageFAB)

        // Set up RecyclerView
        messagesRecyclerView.layoutManager = LinearLayoutManager(context)
        messagesAdapter = MessagesAdapter(messages) { message ->
            openChat(message)
        }
        messagesRecyclerView.adapter = messagesAdapter

        // Set up FAB to create a new message
        newMessageFAB.setOnClickListener {
            startNewMessage()
        }

        return binding
    }

    // Open a chat with the selected message
    private fun openChat(message: Message) {
        // Here you would navigate to the chat screen, passing the selected message's info
        Toast.makeText(context, "Opening chat with ${message.userName}", Toast.LENGTH_SHORT).show()
    }

    // Start a new message
    private fun startNewMessage() {
        // Here you would navigate to the new message screen
        Toast.makeText(context, "Starting a new message", Toast.LENGTH_SHORT).show()
    }
}
