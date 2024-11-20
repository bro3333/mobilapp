package com.example.chatbox

import android.content.Context
import android.content.SharedPreferences

class UserManager(context: Context) {

    // SharedPreferences'i başlatır ve "UserPreferences" adlı bir dosya oluşturur.
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    companion object {
        // Anahtar isimlerini sabit olarak tanımlarız, böylece her yerden ulaşılabilir.
        private const val EMAIL_KEY = "email"
        private const val PASSWORD_KEY = "password"
    }

    // E-posta ve şifreyi kaydeden fonksiyon
    fun saveUser(email: String, password: String) {
        // `SharedPreferences`'e e-posta ve şifreyi kaydeder.
        sharedPreferences.edit()
            .putString(EMAIL_KEY, email)
            .putString(PASSWORD_KEY, password)
            .apply()
    }

    // Kaydedilen e-posta ve şifreyi döndüren fonksiyon
    fun getUserCredentials(): Pair<String?, String?> {
        // `SharedPreferences`'ten kayıtlı e-posta ve şifreyi alır
        val email = sharedPreferences.getString(EMAIL_KEY, null)
        val password = sharedPreferences.getString(PASSWORD_KEY, null)

        // Eğer e-posta ve şifre yoksa null döndürür
        return Pair(email, password)
    }

    // Girilen e-posta ve şifrenin kayıtlı olanlarla eşleşip eşleşmediğini kontrol eder
    fun isUserValid(email: String, password: String): Boolean {
        // Kayıtlı e-posta ve şifre ile girilen değerleri karşılaştırır
        val (savedEmail, savedPassword) = getUserCredentials()

        // Eğer e-posta veya şifre bulunamıyorsa, geçerli değildir
        return savedEmail == email && savedPassword == password
    }

    // Kullanıcı verilerini temizleyen fonksiyon (çıkış yapmak için)
    fun clearUser() {
        // Tüm kayıtlı bilgileri siler (çıkış yapıldığında çağrılacak)
        sharedPreferences.edit().clear().apply()
    }
}
