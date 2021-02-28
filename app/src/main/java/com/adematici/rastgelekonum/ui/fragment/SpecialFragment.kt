package com.adematici.rastgelekonum.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.databinding.FragmentSpecialBinding

class SpecialFragment : Fragment() {

    private lateinit var binding: FragmentSpecialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecialBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.special_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_info -> {
                return true
            }
        }
        return true
    }

}