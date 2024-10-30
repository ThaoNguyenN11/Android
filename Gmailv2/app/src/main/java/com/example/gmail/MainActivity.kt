package com.example.gmail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.EmailAdapter
import com.example.gmail.EmailModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailList = listOf(
            EmailModel(
                resources.getIdentifier("thumb_e", "mipmap", packageName),
                "LearningHub.com",
                "1:15 PM",
                "$29 Only (Limited Time Offer)",
                "Join our coding bootcamp to become a web developer!"),
            EmailModel(
                resources.getIdentifier("thumb_c", "mipmap", packageName),
                "John Doe",
                "12:50 PM",
                "Feedback needed for our new feature",
                "We'd love to hear your thoughts on the new updates."),
            EmailModel(
                resources.getIdentifier("thumb_t", "mipmap", packageName),
                "DesignPro.com",
                "12:30 PM",
                "Free Design Resources Available Now!",
                "Download free templates for your next project."),
            EmailModel(
                resources.getIdentifier("thumb_s", "mipmap", packageName),
                "Customer Service",
                "11:45 AM",
                "Your Order Confirmation",
                "Thank you for your purchase! Order ID: 12345."),
            EmailModel(
                resources.getIdentifier("thumb_m", "mipmap", packageName),
                "Alex from Marketing",
                "11:00 AM",
                "New Product Launch Announcement",
                "We're excited to announce our latest product release!"),
            EmailModel(
                resources.getIdentifier("thumb_e", "mipmap", packageName),
                "LearningHub.com",
                "1:15 PM",
                "$29 Only (Limited Time Offer)",
                "Join our coding bootcamp to become a web developer!"),
            EmailModel(
                resources.getIdentifier("thumb_c", "mipmap", packageName),
                "John Doe",
                "12:50 PM",
                "Feedback needed for our new feature",
                "We'd love to hear your thoughts on the new updates."),
            EmailModel(
                resources.getIdentifier("thumb_t", "mipmap", packageName),
                "DesignPro.com",
                "12:30 PM",
                "Free Design Resources Available Now!",
                "Download free templates for your next project."),
            EmailModel(
                resources.getIdentifier("thumb_s", "mipmap", packageName),
                "Customer Service",
                "11:45 AM",
                "Your Order Confirmation",
                "Thank you for your purchase! Order ID: 12345."),
            EmailModel(
                resources.getIdentifier("thumb_m", "mipmap", packageName),
                "Alex from Marketing",
                "11:00 AM",
                "New Product Launch Announcement",
                "We're excited to announce our latest product release!"),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EmailAdapter(emailList)
    }
}