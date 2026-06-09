package com.example.mobileapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailAktivitasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_aktivitas)

        // Inisialisasi View
        val tvJudul: TextView = findViewById(R.id.tvDetailJudul)
        val tvDeskripsi: TextView = findViewById(R.id.tvDetailDeskripsi)
        val btnBack: TextView = findViewById(R.id.btnBack)

        // Tangkap data dari Intent
        val judul = intent.getStringExtra("EXTRA_JUDUL")
        val deskripsi = intent.getStringExtra("EXTRA_DESKRIPSI")

        // Tampilkan data ke layar
        tvJudul.text = judul ?: "Tidak ada judul"
        tvDeskripsi.text = deskripsi ?: "Tidak ada deskripsi"

        // Fungsi tombol kembali
        btnBack.setOnClickListener {
            finish()
        }
    }
}