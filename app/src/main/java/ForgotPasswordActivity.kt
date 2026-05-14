package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)

        val btnSendReset = findViewById<Button>(R.id.btnSendReset)
        val tvBack = findViewById<TextView>(R.id.tvBackToLoginFromForgot)

        btnSendReset.setOnClickListener {
            Toast.makeText(this, "Link reset telah dikirim ke email", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvBack.setOnClickListener {
            finish()
        }
    }
}