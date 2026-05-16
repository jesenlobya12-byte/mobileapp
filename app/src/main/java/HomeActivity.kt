package com.example.mobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Pastikan layout sudah benar
        setContentView(R.layout.activity_home)

        // 2. Setup Toolbar (Gunakan try-catch agar jika ID salah tidak crash)
        try {
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            if (toolbar != null) {
                setSupportActionBar(toolbar)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 3. Inisialisasi Tombol dengan Safe Call
        val btnOpenMaps = findViewById<Button>(R.id.btnOpenMaps)
        val btnOpenCamera = findViewById<Button>(R.id.btnOpenCamera)
        val btnShareText = findViewById<Button>(R.id.btnShareText)
        val btnOpenNews = findViewById<Button>(R.id.btnOpenNews)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnOpenMaps?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Jakarta"))
            startActivity(intent)
        }

        btnOpenCamera?.setOnClickListener {
            startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        btnShareText?.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Halo dari aplikasi saya!")
            }
            startActivity(Intent.createChooser(intent, "Share via"))
        }

        btnOpenNews?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
        }

        btnLogout?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // 4. Setup RecyclerView
        val rvAktivitas = findViewById<RecyclerView>(R.id.rvAktivitas)
        if (rvAktivitas != null) {
            val listData = listOf("Tugas 1", "Tugas 2", "Tugas 3", "Tugas 4")
            rvAktivitas.layoutManager = LinearLayoutManager(this)
            rvAktivitas.adapter = AktivitasAdapter(listData)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_profile -> {
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_settings -> {
                startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}