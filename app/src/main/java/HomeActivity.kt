package com.example.mobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inisialisasi Firebase Auth untuk fungsi Logout
        auth = FirebaseAuth.getInstance()

        // Inisialisasi semua komponen ID dari XML
        val btnMenu = findViewById<TextView>(R.id.btnMenu)
        val tvLogout = findViewById<TextView>(R.id.tvLogout)
        val btnMaps = findViewById<Button>(R.id.btnMaps)
        val btnKamera = findViewById<Button>(R.id.btnKamera)
        val btnBagikan = findViewById<Button>(R.id.btnBagikan)
        val btnSosmed = findViewById<Button>(R.id.btnSosmed)
        val rvBeritaTerbaru = findViewById<RecyclerView>(R.id.rvBeritaTerbaru)

        // 1. Aksi Tombol Menu: Berpindah ke Halaman ProfilActivity
        btnMenu?.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        // 2. Aksi Tombol Logout: Keluar Sesi Akun Firebase Cloud secara Aman
        tvLogout?.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Berhasil keluar akun", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Tutup HomeActivity permanen
        }

        // 3. Implicit Intent: Buka Titik Lokasi Google Maps
        btnMaps?.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=Kampus")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com"))
                startActivity(browserIntent)
            }
        }

        // 4. Implicit Intent: Membuka Kamera Perangkat HP
        btnKamera?.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivity(takePictureIntent)
            } else {
                Toast.makeText(this, "Aplikasi Kamera tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Implicit Intent: Bagikan Teks Aplikasi ke Sosmed Lain (WhatsApp, dll)
        btnBagikan?.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Yuk cek aplikasi MobileApp terbaru saya!")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }

        // 6. Implicit Intent: Membuka Website Browser Eksternal
        btnSosmed?.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"))
            startActivity(webIntent)
        }

        // =============================================================
        // SETUP RECYCLERVIEW BERITA TERBARU
        // =============================================================
        val dataBerita = SumberDataBerita.daftarBeritaTerbaru
        val beritaAdapter = BeritaAdapter(dataBerita)
        rvBeritaTerbaru.layoutManager = LinearLayoutManager(this)
        rvBeritaTerbaru.adapter = beritaAdapter
    }
}