package com.example.chatbox

data class MessageModel(
    val isUser: Boolean,
    val message: String,
    val role: String
)
