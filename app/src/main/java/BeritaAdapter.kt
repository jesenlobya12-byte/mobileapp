package com.example.mobileapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeritaAdapter(private val listBerita: List<Aktivitas>) :
    RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJudulBerita: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val berita = listBerita[position]
        holder.tvJudulBerita.text = berita.judul

        // BARIS BARU: Memaksa warna tulisan judul menjadi PUTIH
        holder.tvJudulBerita.setTextColor(Color.WHITE)

        // Opsional: Memberikan sedikit ruang/padding atas bawah agar teks tidak terlalu rapat
        holder.tvJudulBerita.setPadding(0, 20, 0, 20)

        // Ketika item berita diklik, buka Halaman Detail
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailAktivitasActivity::class.java).apply {
                putExtra("EXTRA_JUDUL", berita.judul)
                putExtra("EXTRA_DESKRIPSI", berita.deskripsi)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listBerita.size
}