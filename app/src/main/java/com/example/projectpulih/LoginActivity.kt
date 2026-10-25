package com.example.projectpulih

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectpulih.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.loginScroll) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
    }

    private fun setupListeners() {
        // Toggle visibilitas kata sandi
        binding.btnPasswordToggle.setOnClickListener {
            togglePasswordVisibility()
        }

        // Tombol Masuk
        binding.btnMasuk.setOnClickListener {
            handleLogin()
        }

        // Tombol Masuk dengan Google
        binding.btnGoogle.setOnClickListener {
            saveLoginStateAndNavigate("Masuk dengan Google berhasil.")
        }

        // Tombol Pernapasan Singkat
        binding.btnQuickBreathing.setOnClickListener {
            showQuickBreathingDialog()
        }

        // Lupa Kata Sandi
        binding.txtForgotPassword.setOnClickListener {
            Toast.makeText(this, "Tautan reset kata sandi telah dikirim ke email Anda.", Toast.LENGTH_SHORT).show()
        }

        // Daftar Sekarang
        binding.txtRegister.setOnClickListener {
            Toast.makeText(this, "Membuka formulir pendaftaran akun baru.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        if (isPasswordVisible) {
            binding.txtInputPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.btnPasswordToggle.setImageResource(R.drawable.ic_eye_off)
        } else {
            binding.txtInputPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.btnPasswordToggle.setImageResource(R.drawable.ic_eye)
        }
        // Pastikan kursor tetap berada di akhir teks
        binding.txtInputPassword.setSelection(binding.txtInputPassword.text.length)
    }

    private fun handleLogin() {
        val email = binding.txtInputEmail.text.toString().trim()
        val password = binding.txtInputPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.txtInputEmail.error = "Silakan masukkan alamat email"
            binding.txtInputEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.txtInputEmail.error = "Format email tidak valid (contoh: nama@email.com)"
            binding.txtInputEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            binding.txtInputPassword.error = "Silakan masukkan kata sandi"
            binding.txtInputPassword.requestFocus()
            return
        }

        if (password.length < 6) {
            binding.txtInputPassword.error = "Kata sandi minimal 6 karakter"
            binding.txtInputPassword.requestFocus()
            return
        }

        // Validasi berhasil, simpan isLogin ke SharedPreferences
        saveLoginStateAndNavigate("Masuk berhasil! Selamat datang di Pulih.")
    }

    private fun saveLoginStateAndNavigate(successMessage: String) {
        val sharedPreferences = getSharedPreferences(SplashActivity.PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putBoolean(SplashActivity.KEY_IS_LOGIN, true)
            .apply()

        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showQuickBreathingDialog() {
        AlertDialog.Builder(this)
            .setTitle("🌿 Pernapasan Singkat (1 Menit)")
            .setMessage("Tarik napas perlahan selama 4 detik...\nTahan selama 4 detik...\nHembuskan perlahan selama 6 detik.\n\nFokuskan perhatian pada saat ini dan lepaskan beban pikiran.")
            .setPositiveButton("Selesai") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Pikiran Anda kini lebih tenang.", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
