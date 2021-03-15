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
    private lateinit var sp: SharedPreferences
    private var latitude: Double? = null
    private var longitude: Double? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentRandomBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.buttonRandomLocation.setOnClickListener {
            createRandomLocation()
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

    fun createRandomLocation(){
        latitude = Random.nextDouble(-85.0,85.0)
        longitude = Random.nextDouble(-180.0,180.0)

        if(((latitude!!<-33.0 || latitude!!>34.0)&&(longitude!!<-15.0 || longitude!!>35.0)) // Africa
                ||((latitude!!>10.0 || latitude!!<-54.0)&&(longitude!!<-78.0 || longitude!!>-45.0)) // South America
                ||((latitude!!>69.0 || latitude!!<15.0)&&(longitude!!<-139.0 || longitude!!>-70.0)) // North America
                ||((latitude!!>75.0 || latitude!!<35.0)&&(longitude!!<-13.0 || longitude!!>160.0)) // Europe
                ||((latitude!!>35.0 || latitude!!<10.0)&&(longitude!!<35.0 || longitude!!>148.0)) // Asia
                ||((latitude!!>-12.0 || latitude!!<-38.0)&&(longitude!!<112.0 || longitude!!>151.0)) // Australia
                ||((latitude!!>-12.0 || latitude!!<-38.0)&&(longitude!!<112.0 || longitude!!>151.0))){
            latitude = Random.nextDouble(-85.0,85.0)
            longitude = Random.nextDouble(-180.0,180.0)
            if(((latitude!!<-33.0 || latitude!!>34.0)&&(longitude!!<-15.0 || longitude!!>35.0)) // Africa
                    ||((latitude!!>10.0 || latitude!!<-54.0)&&(longitude!!<-78.0 || longitude!!>-45.0)) // South America
                    ||((latitude!!>69.0 || latitude!!<15.0)&&(longitude!!<-139.0 || longitude!!>-70.0)) // North America
                    ||((latitude!!>75.0 || latitude!!<35.0)&&(longitude!!<-13.0 || longitude!!>160.0)) // Europe
                    ||((latitude!!>35.0 || latitude!!<10.0)&&(longitude!!<35.0 || longitude!!>148.0)) // Asia
                    ||((latitude!!>-12.0 || latitude!!<-38.0)&&(longitude!!<112.0 || longitude!!>151.0)) // Australia
                    ||((latitude!!>-12.0 || latitude!!<-38.0)&&(longitude!!<112.0 || longitude!!>151.0))){
                latitude = Random.nextDouble(-85.0,85.0)
                longitude = Random.nextDouble(-180.0,180.0)
            }
        }
    }
}