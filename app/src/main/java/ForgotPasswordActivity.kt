package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        val etEmailForgot = findViewById<EditText>(R.id.etEmailForgot)
        val btnSendReset = findViewById<Button>(R.id.btnSendReset)
        val tvBack = findViewById<TextView>(R.id.tvBackToLoginFromForgot)

        // Aksi Tombol Kirim Instruksi Lupa Password
        btnSendReset.setOnClickListener {
            val email = etEmailForgot.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Silakan masukkan email Anda terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Link reset telah dikirim ke email Anda!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        tvBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}