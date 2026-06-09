package com.example.mobileapp

data class Aktivitas(
    val id: String = "",
    val judul: String = "",
    val deskripsi: String = ""
)

object SumberDataBerita {
    val daftarBeritaTerbaru: List<Aktivitas> = listOf(
        Aktivitas(
            id = "news_1",
            judul = "Kuliah Praktikum Berjalan Lancar",
            deskripsi = "Mahasiswa antusias mengikuti sesi lab minggu ini di kampus."
        ),
        Aktivitas(
            id = "news_2",
            judul = "Android Studio Update Versi Baru",
            deskripsi = "Simak fitur-fitur mutakhir yang mempermudah proses penyusunan layout XML."
        ),
        Aktivitas(
            id = "news_3",
            judul = "Tips Lulus Tepat Waktu",
            deskripsi = "Atur jadwal belajar mandiri dan selesaikan tugas pemrograman sedini mungkin."
        ),
        Aktivitas(
            id = "news_4",
            judul = "Info Seminar Teknologi",
            deskripsi = "Akan diadakan webinar nasional mengenai pengembangan aplikasi mobile."
        )
    )
}