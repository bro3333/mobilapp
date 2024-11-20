package com.example.chatbox.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbox.R
import java.util.ArrayList

class MessageAdapter(private val list: ArrayList<com.example.chatbox.mesage.MessageModel>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
        val msgTxt: TextView = if (viewType == 0) {
            view.findViewById(R.id.show_message) // Kullanıcı mesajı için
        } else {
            view.findViewById(R.id.response) // Bot mesajı için
        }

        init {
            if (msgTxt == null) {
                Log.e("MessageAdapter", "TextView not found in layout!")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = if (viewType == 0) {
            inflater.inflate(R.layout.chatresponse1, parent, false)
        } else {
            inflater.inflate(R.layout.chatresponse, parent, false)
        }
        return MessageViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = list[position]
        holder.msgTxt.text = message.message
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        val message = list[position]
        return if (message.isUser) 0 else 1
    }
}
