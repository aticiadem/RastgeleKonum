package com.adematici.rastgelekonum.ui.fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.adematici.rastgelekonum.MapsActivity
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.databinding.FragmentRandomBinding
import com.adematici.rastgelekonum.databinding.ZoomCustomDialogBinding
import com.adematici.rastgelekonum.ui.activity.SettingsActivity
import kotlin.random.Random

class RandomFragment : Fragment() {

    private lateinit var binding: FragmentRandomBinding
    private var zoomValue: Float? = null        // 20 çok yakın oluyor, 10 uzak. 15 güzel oluyor.
    private lateinit var sp: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentRandomBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.buttonRandomLocation.setOnClickListener {
            val latitude: Double = Random.nextDouble(-85.0,85.0)
            val longitude: Double = Random.nextDouble(-180.0,180.0)

            activity?.let {
                val intent = Intent(it, MapsActivity::class.java)
                intent.putExtra("info","random")
                intent.putExtra("randomlatitude",latitude)
                intent.putExtra("randomlongitude",longitude)
                it.startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.random_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings ->{
                activity?.let{
                    val intent = Intent (it, SettingsActivity::class.java)
                    it.startActivity(intent)
                }
                return true
            }
            R.id.action_zoom_value -> {
                // Alert Dialog
                val builder = AlertDialog.Builder(requireActivity())
                val dialogBinding: ZoomCustomDialogBinding = ZoomCustomDialogBinding.inflate(layoutInflater)
                builder.setView(dialogBinding.root)
                val mAlertDialog = builder.show()

                val list = arrayOf("1","2","3","4","5","6","7","8","9"
                        ,"10","11","12","13","14","15","16","17","18","19","20")
                val spinnerAdapter = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_item,list)
                dialogBinding.spinner.adapter = spinnerAdapter

                dialogBinding.buttonAlertConfirm.setOnClickListener {
                    val newZoomValue = dialogBinding.spinner.selectedItem
                    sp = activity?.getSharedPreferences("ZoomInfo", Context.MODE_PRIVATE)!!
                    val editor = sp.edit()
                    editor.putFloat("zoomFloat",newZoomValue.toString().toFloat())
                    editor.apply()
                    mAlertDialog.dismiss()
                }
                dialogBinding.buttonAlertCancel.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }
        return true
    }

}