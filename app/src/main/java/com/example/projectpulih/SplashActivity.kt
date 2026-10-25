package com.example.projectpulih

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectpulih.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.splashRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tunda 1.5 detik untuk menampilkan branding Pulih sebelum routing
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStateAndNavigate()
        }, 1500)
    }

    private fun checkLoginStateAndNavigate() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isLogin = sharedPreferences.getBoolean(KEY_IS_LOGIN, false)

        val targetIntent = if (isLogin) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(targetIntent)
        finish()
    }

    companion object {
        const val PREFS_NAME = "pulih_preferences"
        const val KEY_IS_LOGIN = "isLogin"
    }
}
