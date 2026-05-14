package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val btnDoRegister = findViewById<Button>(R.id.btnDoRegister)
        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)

        btnDoRegister.setOnClickListener {
            // Setelah "daftar", balik ke Login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvBackToLogin.setOnClickListener {
            onBackPressed() // Kembali ke halaman sebelumnya
        }
    }
}