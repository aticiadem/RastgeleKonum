package com.adematici.rastgelekonum.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.database.DatabaseHelper
import com.adematici.rastgelekonum.database.LocationDao

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val dh = DatabaseHelper(this)

        Handler().postDelayed({
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
            //Toast.makeText(this,"loadsettingtrue",Toast.LENGTH_SHORT).show()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Dark Mode closed
            //Toast.makeText(this,"loadsettingfalse",Toast.LENGTH_SHORT).show()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}