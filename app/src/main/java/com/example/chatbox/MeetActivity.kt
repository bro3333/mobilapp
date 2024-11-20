package com.example.chatbox

import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MeetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_meet)
        val textView: TextView = findViewById(R.id.textView3)
        val textView2: TextView = findViewById(R.id.textView6)
        val edittext: EditText = findViewById(R.id.editTextTextEmailAddress)
        val edittext2: EditText = findViewById(R.id.editTextTextPassword)
        val button: Button = findViewById(R.id.button1)


        colorString(textView2)
        renkliMetinOlustur(textView)
        var userManager = UserManager(this)


        button.setOnClickListener {
            val email = edittext.text.toString()
            val password = edittext2.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // kullanıcı bilgilerini kaydet
                userManager.saveUser(email, password)
                Toast.makeText(this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()

                // Kayıttan sonra login sayfasına yönlendir
                val intent = Intent(this, loginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            }

        }



        textView2.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

    }


    private fun colorString(textView2: TextView) {
        val paragraf = "Hesabınız varsa giriş yapınız"

        val startIndex = paragraf.indexOf("giriş yapınız")
        val endIndex = startIndex + "giriş yapınız".length

        val spannableString = SpannableString(paragraf)

        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView2.text = spannableString
    }

    private fun renkliMetinOlustur(textView: TextView) {
        val paragraf = "MERHABA ARKADAŞ ! UYGULAMAYA GİREBİLMENİZ İÇİN KAYIT OLMANIZ GEREKİYOR"


        val startIndex = paragraf.indexOf("KAYIT OLMANIZ")
        val endIndex = startIndex + "KAYIT OLMANIZ".length

        val spannableString = SpannableString(paragraf)
        spannableString.setSpan(
            ForegroundColorSpan(Color.GRAY),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = spannableString
    }
}