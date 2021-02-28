package com.adematici.rastgelekonum.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.adapter.PageAdapter
import com.adematici.rastgelekonum.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = PageAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->
            when(position){
                0 -> tab.text = "Random"
                1 -> tab.text = "Special"
                2 -> tab.text = "Records"
            }
        }.attach()
    }
}