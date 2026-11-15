package com.example.projectpulih

/**
 * Data Model untuk item RecyclerView Riwayat Jurnal (Pertemuan 10)
 */
data class JournalModel(
    val id: Int,
    val moodName: String,
    val moodEmoji: String,
    val moodType: String, // "tenang", "sangat_baik", "biasa", "lelah"
    val date: String,
    val content: String,
    val tags: List<String>,
    val period: String = "weekly" // "all", "weekly", "monthly"
)
