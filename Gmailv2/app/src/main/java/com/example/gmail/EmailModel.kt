package com.example.gmail

data class EmailModel(
    val avatarResId: Int,
    val senderName: String,
    val timestamp: String,
    val subject: String,
    val previewText: String
) {
    var isStarred = false
}