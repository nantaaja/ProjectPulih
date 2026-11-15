package com.example.projectpulih

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.projectpulih.databinding.ItemJournalBinding

/**
 * Adapter RecyclerView untuk Riwayat Jurnal (Sesuai Pola Modul Pertemuan 10)
 */
class JournalAdapter(
    private val journalList: List<JournalModel>,
    private val onItemClick: (JournalModel) -> Unit
) : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    inner class JournalViewHolder(val binding: ItemJournalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val binding = ItemJournalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return JournalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val item = journalList[position]
        with(holder.binding) {
            tvMoodEmoji.text = item.moodEmoji
            tvMoodName.text = item.moodName
            tvJournalDate.text = item.date
            tvJournalContent.text = item.content

            val context = holder.itemView.context
            when (item.moodType.lowercase()) {
                "tenang" -> {
                    layoutMoodBadge.setBackgroundResource(R.drawable.bg_badge_tenang)
                    tvMoodName.setTextColor(ContextCompat.getColor(context, R.color.forest_sage_dark))
                }
                "sangat_baik", "sangat" -> {
                    layoutMoodBadge.setBackgroundResource(R.drawable.bg_badge_sangat_baik)
                    tvMoodName.setTextColor(ContextCompat.getColor(context, R.color.terracotta_peach_dark))
                }
                "biasa", "biasa_saja" -> {
                    layoutMoodBadge.setBackgroundResource(R.drawable.bg_badge_biasa)
                    tvMoodName.setTextColor(ContextCompat.getColor(context, R.color.mood_biasa_text))
                }
                "lelah" -> {
                    layoutMoodBadge.setBackgroundResource(R.drawable.bg_badge_lelah)
                    tvMoodName.setTextColor(ContextCompat.getColor(context, R.color.mood_lelah_text))
                }
                else -> {
                    layoutMoodBadge.setBackgroundResource(R.drawable.bg_badge_tenang)
                    tvMoodName.setTextColor(ContextCompat.getColor(context, R.color.forest_sage_dark))
                }
            }

            if (item.tags.isNotEmpty()) {
                tvTag1.visibility = View.VISIBLE
                tvTag1.text = item.tags[0]
            } else {
                tvTag1.visibility = View.GONE
            }

            if (item.tags.size > 1) {
                tvTag2.visibility = View.VISIBLE
                tvTag2.text = item.tags[1]
            } else {
                tvTag2.visibility = View.GONE
            }

            root.setOnClickListener { onItemClick(item) }
            btnJournalOptions.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = journalList.size
}
