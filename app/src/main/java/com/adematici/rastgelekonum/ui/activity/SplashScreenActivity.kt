package com.adematici.rastgelekonum.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.adematici.rastgelekonum.R
import com.google.android.gms.ads.MobileAds

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },800)

        loadSetting()
    }

    private fun loadSetting(){
        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val checkNight: Boolean = sp.getBoolean("DARKMODE",false)
        if(checkNight){
            // Dark Mode open
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Dark Mode closed
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}