package com.example.chatbox

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbox.adapter.MessageAdapter
import com.example.chatbox.databinding.ActivityChatBoxBinding
import com.example.chatbox.mesage.MessageModel
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.SocketTimeoutException

class ChatBoxActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBoxBinding

    private var list = ArrayList<MessageModel>()
    private lateinit var adapter: MessageAdapter
    private val TAG = "ChatBoxActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val close = findViewById<ImageView>(R.id.imageView5)

        close.setOnClickListener {
            val close = Intent(this, HomeScreenActivity::class.java)
            startActivity(close)
        }

        // RecyclerView ayarları
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        binding.recyclerView.layoutManager = mLayoutManager

        adapter = MessageAdapter(list)
        binding.recyclerView.adapter = adapter

        // Mesaj gönderme butonuna tıklama
        binding.imageView8.setOnClickListener {
            val userMessage = binding.editTextText.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                addMessageToChat(userMessage, isUser = true)
                binding.editTextText.text.clear()
                fetchAIResponse(userMessage)
            } else {
                Toast.makeText(this, "Mesaj boş olamaz!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Mesajı listeye ekle ve RecyclerView'i güncelle
    private fun addMessageToChat(message: String, isUser: Boolean) {
        list.add(MessageModel(message, isUser))
        adapter.notifyItemInserted(list.size - 1)
        binding.recyclerView.scrollToPosition(list.size - 1)
    }

    // AI yanıtını al ve listeye ekle
    private fun fetchAIResponse(userMessage: String) {
        lifecycleScope.launch {
            try {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash",
                    apiKey = "AIzaSyARHBU6dyvVEp71zvrsdRSexZ2z_wl9QD4"
                )

                val response = withContext(Dispatchers.IO) {
                    generativeModel.generateContent(userMessage)
                }

                val aiMessage = response?.text?.takeIf { it.isNotBlank() }
                    ?: "AI'den geçerli bir yanıt alınamadı."
                addMessageToChat(aiMessage, isUser = false)
            } catch (e: SocketTimeoutException) {
                Toast.makeText(this@ChatBoxActivity, "Bağlantı zaman aşımına uğradı. Lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Toast.makeText(this@ChatBoxActivity, "Ağ hatası oluştu. Lütfen bağlantınızı kontrol edin.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@ChatBoxActivity, "Beklenmeyen bir hata: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

