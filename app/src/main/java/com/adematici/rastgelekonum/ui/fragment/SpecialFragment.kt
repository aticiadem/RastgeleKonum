package com.adematici.rastgelekonum.ui.fragment

import android.app.AlertDialog
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
                showInfo()
                return true
            }
        }
        return true
    }

    private fun showInfo(){
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle("Search Information")
        alertDialog.setMessage("- Ranges;\n" +
                "  Latitude:  (-85)  ~ (+85)\n" +
                "  Longitude: (-180) ~ (+180)\n" +
                "\n" + " Example: 39.925054 / 32.8347552\n\n"+
                "- If you type the coordinates correctly and press the (Go To Location ) button, you will go to the coordinates you entered.\n" +
                "\n- If you are press to (Go To A Random coordinate) button, you will go to random coordinate in the world." +
                "\n\n- If you press the pointer and then the map icon below, you can connect to Google Maps and get better views.")
        alertDialog.show()
    }

}