package com.adematici.rastgelekonum.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.databinding.FragmentSpecialBinding
import com.adematici.rastgelekonum.ui.activity.MapsActivity

class SpecialFragment : Fragment() {

    private lateinit var binding: FragmentSpecialBinding
    private var latitude: Double? = null
    private var longitude: Double? = null

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

        binding.buttonGoLocation.setOnClickListener {
            try {
                if((binding.editTextLatitude.text.toString().toDouble() <= 85.0 && binding.editTextLatitude.text.toString().toDouble() >= -85.0)
                        && (binding.editTextLongitude.text.toString().toDouble() <= 180.0 && binding.editTextLongitude.text.toString().toDouble() >= -180.0)){
                    latitude = binding.editTextLatitude.text.toString().toDouble()
                    longitude = binding.editTextLongitude.text.toString().toDouble()
                    val intent = Intent(requireActivity(), MapsActivity::class.java)
                    intent.putExtra("info","manual")
                    intent.putExtra("notrandomlatitude",latitude)
                    intent.putExtra("notrandomlongitude",longitude)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireActivity(),"Latitude Range: -85/+85 \nLongitude Range: -180/+180",Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(requireActivity(),"Related fields cannot be left blank!",Toast.LENGTH_SHORT).show()
            }
        }
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