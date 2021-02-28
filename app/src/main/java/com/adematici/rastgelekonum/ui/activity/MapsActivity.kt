package com.adematici.rastgelekonum.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adematici.rastgelekonum.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMapsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}