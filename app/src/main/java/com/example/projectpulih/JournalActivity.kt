package com.example.projectpulih

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projectpulih.databinding.ActivityJournalBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Layar Riwayat Jurnal (Penerapan TabLayout, ViewPager2, TabLayoutMediator, & RecyclerView - Pertemuan 10)
 */
class JournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJournalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabsAndViewPager()
        setupBottomNav()
        setupActions()
    }

    /**
     * Menghubungkan TabLayout dan ViewPager2 dengan TabLayoutMediator (Sesuai Tahap 1.4 & 2.1 Modul 10)
     */
    private fun setupTabsAndViewPager() {
        // 1. Inisialisasi adapter ViewPager2
        val tabsAdapter = JournalTabsAdapter(this)

        // 2. Pasang adapter ke ViewPager2
        binding.viewPagerJournal.adapter = tabsAdapter

        // 3. Hubungkan TabLayout dan ViewPager2 dengan TabLayoutMediator
        TabLayoutMediator(binding.tabLayoutJournal, binding.viewPagerJournal) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Semua"
                    // Badge angka (Tahap 2.1 Modul & Sesuai Desain Figma: 12 Jurnal)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 12
                    badge.backgroundColor = ContextCompat.getColor(this, R.color.forest_sage)
                }
                1 -> {
                    tab.text = "Minggu Ini"
                }
                2 -> {
                    tab.text = "Bulan Ini"
                }
            }
        }.attach()
    }

    private fun setupActions() {
        binding.btnNotification.setOnClickListener {
            Toast.makeText(this, "Tidak ada notifikasi baru.", Toast.LENGTH_SHORT).show()
        }

        binding.btnFilterJournal.setOnClickListener {
            Toast.makeText(this, "Filter lanjutan: Menampilkan jurnal terverifikasi.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNav() {
        binding.btnNavBeranda.setOnClickListener {
            finish() // Kembali ke Beranda
        }

        binding.btnNavJurnal.setOnClickListener {
            // Sudah di halaman Jurnal
        }

        binding.btnNavTambah.setOnClickListener {
            Toast.makeText(this, "Tambah entri jurnal atau refleksi baru", Toast.LENGTH_SHORT).show()
        }

        binding.btnNavPanduan.setOnClickListener {
            startActivity(Intent(this, TutorialMessageActivity::class.java))
        }
    }
}
