package com.example.chatbox

import android.annotation.SuppressLint
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

class loginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val textView: TextView = findViewById(R.id.textView3)
        val textView2: TextView = findViewById(R.id.textView6)
        val edittext: EditText = findViewById(R.id.editTextTextEmailAddress)
        val edittext2: EditText = findViewById(R.id.editTextTextPassword)
        val button: Button = findViewById(R.id.button1)

        colorString(textView2)
        renkliMetinOlustur(textView)

        // UserManager örneğini başlat
        var userManager = UserManager(this)


        button.setOnClickListener {
            val editText = edittext.text.toString()
            val editText2 = edittext2.text.toString()


            if(userManager.isUserValid(editText, editText2)){
                // Kullanıcı geçerliyse MainActivity'ye geç
                val intent = Intent(this, HomeScreenActivity::class.java)
                startActivity(intent)
                finish()
            }


            else{
                // kullanıcı kayıtlı değilse toast mesaj ver ve meetActivity geç
                Toast.makeText(this, "Kullanıcı adı veya şifre hatalı yada sisteme kayıtlı değil kullanıcı", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MeetActivity::class.java)
                startActivity(intent)
            }

        }

        textView2.setOnClickListener {
            val intent = Intent(this, MeetActivity::class.java)
            startActivity(intent)
        }

    }



    private fun colorString(textView2: TextView) {
        val paragraf = "Hesabınız yoksa kayıt olunuz"

        val startIndex = paragraf.indexOf("kayıt olunuz")
        val endIndex = startIndex + "kayıt olunuz".length

        val spannableString = SpannableString(paragraf)

        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),  // android.graphics.Color.RED
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        textView2.text = spannableString
    }

    private fun renkliMetinOlustur(textView: TextView) {
        val paragraf = "MERHABA ARKADAŞ ! UYGULAMAYA GİREBİLMENİZ İÇİN GİRİŞ YAPMANIZ GEREKİYOR"


        val startIndex = paragraf.indexOf("GİRİŞ YAPMANIZ")
        val endIndex = startIndex + "GİRİŞ YAPMANIZ".length

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