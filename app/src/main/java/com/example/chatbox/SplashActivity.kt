package com.example.chatbox

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        renkliMetinOlustur(textView)




        button.setOnClickListener{
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
    }


    private fun renkliMetinOlustur(textView: TextView) {
        val paragraf = "Hoş geldiniz! Yapay zeka destekli uygulamamıza giriş yaptınız. Size en iyi deneyimi sunmak için buradayız. Keyifli keşifler dileriz!"

        val startIndex = paragraf.indexOf("Yapay zeka destekli uygulamamıza")
        val endIndex = startIndex + "Yapay zeka destekli uygulamamıza".length

        val spannableString = SpannableString(paragraf)
        spannableString.setSpan(
            ForegroundColorSpan(Color.GRAY),  // android.graphics.Color.RED
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableString
    }
}
