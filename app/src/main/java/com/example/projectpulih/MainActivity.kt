package com.example.projectpulih

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectpulih.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Data List Target Self-Care
    private val selfCareList = mutableListOf<SelfCareItem>()
    private lateinit var selfCareAdapter: SelfCareAdapter

    // Index mood yang terpilih (default: 1 = Tenang)
    private var selectedMoodIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        setupMoodSelector()
        setupSelfCareListView()
        setupBentoActions()
        setupToolbarActions()
        setupBottomNav()
    }

    private fun setupMoodSelector() {
        val moodButtons = listOf(
            binding.btnMoodVeryHappy,
            binding.btnMoodCalm,
            binding.btnMoodNeutral,
            binding.btnMoodTired,
            binding.btnMoodAnxious
        )

        val moodMessages = listOf(
            "Energi positifmu luar biasa hari ini! Pertahankan kebahagiaan ini.",
            "Ketenangan adalah awal dari pemulihan jiwa yang utuh.",
            "Hari biasa tetaplah hari yang berharga untuk disyukuri.",
            "Tubuhmu butuh jeda. Istirahatlah sejenak dan bernapaslah.",
            "Kecemasan ini sementara. Tarik napas dalam-dalam, kamu aman di sini."
        )

        moodButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                updateMoodSelection(index, moodButtons)
                Toast.makeText(this, moodMessages[index], Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateMoodSelection(selectedIndex: Int, moodButtons: List<LinearLayout>) {
        selectedMoodIndex = selectedIndex
        moodButtons.forEachIndexed { index, linearLayout ->
            if (index == selectedIndex) {
                linearLayout.setBackgroundResource(R.drawable.bg_mood_selected)
                val label = linearLayout.getChildAt(1) as? TextView
                label?.setTextColor(ContextCompat.getColor(this, R.color.forest_sage_dark))
            } else {
                linearLayout.setBackgroundResource(R.drawable.bg_mood_unselected)
                val label = linearLayout.getChildAt(1) as? TextView
                label?.setTextColor(ContextCompat.getColor(this, R.color.dark_slate))
            }
        }
    }

    /**
     * Implementasi Poin 2: ListView dengan Custom Adapter
     */
    private fun setupSelfCareListView() {
        selfCareList.clear()
        selfCareList.add(SelfCareItem(1, "Istirahat dari sosmed 30 menit", "12:30", true))
        selfCareList.add(SelfCareItem(2, "Tulis 1 hal yang disyukuri", "08:15", true))
        selfCareList.add(SelfCareItem(3, "Tidur tepat waktu (sebelum 23.00)", "Malam", false))

        selfCareAdapter = SelfCareAdapter(this, selfCareList) { position ->
            val item = selfCareList[position]
            item.isCompleted = !item.isCompleted
            selfCareAdapter.notifyDataSetChanged()
            updateSelfCareProgress()
        }

        binding.lvSelfCare.adapter = selfCareAdapter
        updateSelfCareProgress()
    }

    private fun updateSelfCareProgress() {
        // Asumsi dari total 5 target, ada 1 target di luar list ini yang sudah selesai (misal air minum pagi)
        var completed = 1
        selfCareList.forEach { if (it.isCompleted) completed++ }

        val total = 5
        val remaining = total - completed
        val percent = (completed * 100) / total

        binding.txtSelfCareStatus.text = "$completed/$total Selesai"
        binding.progressBarSelfCare.progress = percent
        binding.txtSelfCarePercent.text = "$percent% tercapai"
        binding.txtSelfCareRemaining.text = if (remaining > 0) "$remaining target tersisa" else "Semua target selesai! 🎉"
    }

    private fun setupBentoActions() {
        // Tombol Latihan Napas 4-7-8
        binding.btnStartBreathing.setOnClickListener {
            showBreathing478Dialog()
        }

        // Catatan Pagi
        binding.cardMorningNote.setOnClickListener {
            Toast.makeText(this, "Memutar audio pemandu: Temukan ketenangan dalam hening (4 menit)", Toast.LENGTH_SHORT).show()
        }

        // Simpan perasaan (Heart)
        binding.btnHeartMood.setOnClickListener {
            Toast.makeText(this, "Perasaanmu telah disimpan dalam jurnal hari ini.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showBreathing478Dialog() {
        AlertDialog.Builder(this)
            .setTitle("🌬️ Latihan Napas 4-7-8")
            .setMessage("Teknik relaksasi mendalam untuk menenangkan sistem saraf:\n\n" +
                    "1. Tarik napas melalui hidung perlahan (4 detik)\n" +
                    "2. Tahan napas dengan nyaman (7 detik)\n" +
                    "3. Hembuskan napas melalui mulut sepenuhnya (8 detik)\n\n" +
                    "Lakukan siklus ini 4 kali putaran.")
            .setPositiveButton("Mulai Latihan") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Latihan napas 3 menit dimulai. Tarik napas...", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Tutup", null)
            .show()
    }

    private fun setupToolbarActions() {
        // Tombol notifikasi
        binding.btnNotification.setOnClickListener {
            showNotificationDialog()
        }

        // Klik Avatar -> opsi Profil & Logout
        binding.btnProfileAvatar.setOnClickListener {
            showUserOptionsDialog()
        }
    }

    private fun showNotificationDialog() {
        AlertDialog.Builder(this)
            .setTitle("🔔 Notifikasi Pulih")
            .setItems(arrayOf(
                "🌿 Selamat! Kamu mempertahankan streak 5 hari.",
                "⏰ Waktunya istirahat dari layar sejenak.",
                "📖 Audio Catatan Pagi baru telah tersedia."
            )) { _, _ -> }
            .setPositiveButton("Tutup", null)
            .show()
    }

    private fun showUserOptionsDialog() {
        val options = arrayOf("Lihat Profil (Nabil)", "Pengaturan Akun", "Keluar (Logout)")
        AlertDialog.Builder(this)
            .setTitle("Akun Pengguna")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> Toast.makeText(this, "Membuka Profil Nabil", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(this, "Membuka Pengaturan", Toast.LENGTH_SHORT).show()
                    2 -> performLogout()
                }
            }
            .show()
    }

    private fun performLogout() {
        val sharedPreferences = getSharedPreferences(SplashActivity.PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putBoolean(SplashActivity.KEY_IS_LOGIN, false)
            .apply()

        Toast.makeText(this, "Anda telah keluar dari akun.", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    /**
     * Implementasi Poin 1: Bottom Navigation
     */
    private fun setupBottomNav() {
        val navItems = listOf(
            Triple(binding.btnNavBeranda, binding.ivNavBeranda, binding.txtNavBeranda),
            Triple(binding.btnNavJurnal, binding.ivNavJurnal, binding.txtNavJurnal),
            Triple(binding.btnNavPanduan, binding.ivNavPanduan, binding.txtNavPanduan)
        )

        navItems.forEach { (layout, icon, text) ->
            layout.setOnClickListener {
                navItems.forEach { (_, ic, tx) ->
                    ic.setColorFilter(ContextCompat.getColor(this, R.color.dark_slate_secondary))
                    tx.setTextColor(ContextCompat.getColor(this, R.color.dark_slate_secondary))
                }
                icon.setColorFilter(ContextCompat.getColor(this, R.color.forest_sage))
                text.setTextColor(ContextCompat.getColor(this, R.color.forest_sage))

                if (text.text == getString(R.string.nav_panduan)) {
                    showSimpleListViewDialog()
                } else {
                    Toast.makeText(this, "Menu: ${text.text}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnNavTambah.setOnClickListener {
            Toast.makeText(this, "Tambah entri jurnal atau refleksi baru", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Demonstrasi ListView dengan simple_list_item (sesuai instruksi: simple list item & custom adapter)
     */
    private fun showSimpleListViewDialog() {
        val panduanList = arrayOf(
            "1. Panduan Pernapasan Sadar (Mindful Breathing)",
            "2. Teknik Grounding 5-4-3-2-1 untuk Menenangkan Diri",
            "3. Meditasi Pemindaian Tubuh (Body Scan)",
            "4. Menulis Jurnal Refleksi Rasa Syukur (Gratitude Journal)",
            "5. Rutinitas Menjelang Tidur Nyenyak (Sleep Hygiene)"
        )

        val dialogView = layoutInflater.inflate(android.R.layout.simple_list_item_1, null)
        val simpleAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, panduanList)

        val listView = ListView(this)
        listView.adapter = simpleAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Membuka ${panduanList[position]}", Toast.LENGTH_SHORT).show()
        }

        AlertDialog.Builder(this)
            .setTitle("🧘 Panduan Mindfulness (Simple List Item)")
            .setView(listView)
            .setPositiveButton("Tutup", null)
            .show()
    }
}
