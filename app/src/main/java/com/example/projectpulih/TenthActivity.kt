package com.example.projectpulih

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projectpulih.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Halaman Praktikum Pertemuan 10 (TabLayout, ViewPager2, TabLayoutMediator, & RecyclerView)
 */
class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar & Back Navigation
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Pertemuan 10"
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // 1. Inisialisasi adapter
        val tabsAdapter = JournalTabsAdapter(this)

        // 2. Pasang adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout dan ViewPager2 (Sesuai Tahap 1.4, 2.1, & 4.7)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Semua"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_document)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 12
                }
                1 -> {
                    tab.text = "Minggu Ini"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_flame)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                2 -> {
                    tab.text = "Bulan Ini"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_emoji_tenang)
                }
            }
        }.attach()
    }
}
