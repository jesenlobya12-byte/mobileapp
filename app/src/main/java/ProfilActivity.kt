package com.example.mobileapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val btnBack = findViewById<Button>(R.id.btnBackFromProfil)
        btnBack.setOnClickListener {
            finish() // Menutup halaman profil dan kembali ke Home
        }
    }
}