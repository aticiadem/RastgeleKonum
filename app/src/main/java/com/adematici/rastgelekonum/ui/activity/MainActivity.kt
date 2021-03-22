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

        val firstText: String = getString(R.string.tab_layout_first)
        val secondText: String = getString(R.string.tab_layout_second)
        val thirdText: String = getString(R.string.tab_layout_third)

        val adapter = PageAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->
            when(position){
                0 -> tab.text = firstText
                1 -> tab.text = secondText
                2 -> tab.text = thirdText
            }
        }.attach()


    }

}