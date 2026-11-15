package com.example.projectpulih

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter ViewPager2 untuk TabLayout Halaman Jurnal (Sesuai Pola Modul Pertemuan 10)
 */
class JournalTabsAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 3

    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> JournalAllFragment()
            1 -> JournalWeeklyFragment()
            2 -> JournalMonthlyFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}
