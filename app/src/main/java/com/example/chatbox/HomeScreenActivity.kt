package com.example.chatbox

import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)

        val imageView = findViewById<ImageView>(R.id.imageView9)
        val imageView1 = findViewById<ImageView>(R.id.imageView)
        val textView = findViewById<TextView>(R.id.textView8)
        ColorString(textView)



        // click

        imageView.setOnClickListener {
            val intent = Intent(this, ChatBoxActivity::class.java)
            startActivity(intent)
        }

        imageView1.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

    }


    private fun ColorString(textView8: TextView) {
        val paragraf = "Merhaba! Ben Yiğit, 16 yaşındayım. Bu uygulamayı mobil uygulama geliştirme dersi için proje olarak geliştirdim. Şu an beta aşamasında olduğu için bazı eksiklikler olabilir, anlayışınız için teşekkürler. Uygulama şu an iOS cihazlarda çalışmıyor ama en kısa zamanda bu sorunu çözmeyi planlıyorum. Destekleriniz için teşekkürler!"

        val startIndex = paragraf.indexOf("Ben Yiğit, 16 yaşındayım")
        val endIndex = paragraf.indexOf("bazı eksiklikler olabilir") + "bazı eksiklikler olabilir".length

        val spannableString = SpannableString(paragraf)

        spannableString.setSpan(
            ForegroundColorSpan(Color.BLACK),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView8.text = spannableString
    }

}