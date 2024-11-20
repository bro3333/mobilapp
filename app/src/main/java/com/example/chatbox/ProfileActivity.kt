package com.example.chatbox

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Görünümlerq
        val imageView = findViewById<ImageView>(R.id.imageView6)
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextEmailAddress3)
        val updateButton = findViewById<Button>(R.id.button2)
        val emailTextView: TextView = findViewById(R.id.textView9)
        val out = findViewById<ImageView>(R.id.imageView4)
        val delete = findViewById<ImageView>(R.id.imageView7)


        // UserManager'i başlat
        val userManager = UserManager(this)

        delete.setOnClickListener{
            // Kullanıcı bilgilerini sil
            userManager.clearUser()

            // SplashActivity'ye yönlendir
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            finish() // Bu aktiviteyi kapat

            // Kullanıcıya mesaj göster
            Toast.makeText(this, "Kullanıcı silindi", Toast.LENGTH_SHORT).show()
        }

        
        out.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Başarıyla Çıkış Yapıldı", Toast.LENGTH_SHORT).show()
        }


        // Kaydedilen e-posta adresini al ve göster
        val (currentEmail, currentPassword) = userManager.getUserCredentials()
        emailTextView.text = currentEmail ?: "E-posta bulunamadı"

        // Ana ekrana dönmek için imageView'e tıklama olayı
        imageView.setOnClickListener {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
        }

        // Güncelleme butonuna tıklama olayı
        updateButton.setOnClickListener {
            // Kullanıcının girdiği yeni değerleri al
            val newEmail = emailEditText.text.toString()
            val newPassword = passwordEditText.text.toString()

            // Güncelleme kontrolleri
            var isUpdated = false

            if (newEmail.isNotBlank() && newEmail != currentEmail) {
                // Yeni e-posta geçerli ve farklı ise güncelle
                userManager.saveUser(newEmail, currentPassword ?: "")
                emailTextView.text = newEmail // TextView'i güncelle
                isUpdated = true
            }

            if (newPassword.isNotBlank() && newPassword != currentPassword) {
                // Yeni şifre geçerli ve farklı ise güncelle
                userManager.saveUser(currentEmail ?: "", newPassword)
                isUpdated = true
            }

            // Sonuç mesajı
            if (isUpdated) {
                Toast.makeText(this, "Bilgiler başarıyla güncellendi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Değişiklik yapmak için farklı değerler girin", Toast.LENGTH_SHORT).show()
            }



        }
    }
}
