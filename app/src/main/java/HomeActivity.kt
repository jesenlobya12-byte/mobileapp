package com.example.mobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu // Import Tambahan
import android.view.MenuItem // Import Tambahan
import android.widget.Button
import android.widget.Toast // Import Tambahan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnOpenMaps = findViewById<Button>(R.id.btnOpenMaps)
        val btnOpenCamera = findViewById<Button>(R.id.btnOpenCamera)
        val btnShareText = findViewById<Button>(R.id.btnShareText)
        val btnOpenNews = findViewById<Button>(R.id.btnOpenNews)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnOpenMaps.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:-6.2088,106.8456?q=Jakarta")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        btnOpenCamera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(cameraIntent)
        }

        btnShareText.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Halo! Ini adalah pesan dari aplikasi saya.")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, "Kirim via:")
            startActivity(shareIntent)
        }

        btnOpenNews.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.detik.com"))
            startActivity(webIntent)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // --- TAMBAHKAN KODE MENU DI BAWAH INI ---

    // 1. Menampilkan Menu (Inflate XML ke UI)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // 2. Menangani Klik pada Item Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                Toast.makeText(this, "Membuka Profil Pengguna", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_settings -> {
                // Membuka Pengaturan HP (Implicit Intent)
                val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
                startActivity(intent)
                return true
            }
            R.id.menu_contact -> {
                // Membuka WhatsApp/Kontak (Implicit Intent)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/628123456789"))
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}