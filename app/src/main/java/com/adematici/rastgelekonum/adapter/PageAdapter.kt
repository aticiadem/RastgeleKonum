package com.adematici.rastgelekonum.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adematici.rastgelekonum.ui.fragment.RandomFragment
import com.adematici.rastgelekonum.ui.fragment.RecordsFragment
import com.adematici.rastgelekonum.ui.fragment.CustomFragment


class PageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RandomFragment()
            1 -> CustomFragment()
            2 -> RecordsFragment()
            else -> RandomFragment()
        }
    }
}