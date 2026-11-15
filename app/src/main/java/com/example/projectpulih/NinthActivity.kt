package com.example.projectpulih

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectpulih.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar setup
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Praktikum Pertemuan 9"
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Form Login / Simpan
        binding.btnLogin.setOnClickListener {
            val name = binding.etEmail.text.toString().trim()
            if (name.isEmpty()) {
                binding.textInputLayout.error = "Nama tidak boleh kosong"
            } else {
                binding.textInputLayout.error = null
                Toast.makeText(this, "Profil tersimpan: $name", Toast.LENGTH_SHORT).show()
            }
        }

        // ChipGroup listener (Tahap 2 & 7.1)
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // GridLayout 6 Tombol Menu (Tahap 4 & 7.2)
        val gridButtons = listOf(
            binding.btnGridMenu1,
            binding.btnGridMenu2,
            binding.btnGridMenu3,
            binding.btnGridMenu4,
            binding.btnGridMenu5,
            binding.btnGridMenu6
        )
        gridButtons.forEach { btn ->
            btn.setOnClickListener {
                Toast.makeText(this, "Menu dipilih: ${btn.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Buka Tutorial Screen
        binding.btnBukaTutorial.setOnClickListener {
            startActivity(Intent(this, TutorialMessageActivity::class.java))
        }
    }
}
