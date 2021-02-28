package com.adematici.rastgelekonum.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.database.DatabaseHelper
import com.adematici.rastgelekonum.database.LocationDao

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val dh = DatabaseHelper(this)

        LocationDao().addLocation(dh,"1","2","ahahahah")
        LocationDao().addLocation(dh,"112","224","buradan selam")
        LocationDao().addLocation(dh,"1124","223","konum iyi")

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
        },800)
    }

}