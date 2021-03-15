package com.adematici.rastgelekonum

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adematici.rastgelekonum.database.DatabaseHelper
import com.adematici.rastgelekonum.database.LocationDao
import com.adematici.rastgelekonum.databinding.MapSaveCustomDialogBinding
import com.adematici.rastgelekonum.databinding.ZoomCustomDialogBinding
import com.adematici.rastgelekonum.ui.activity.MainActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var sp: SharedPreferences
    private var zoomValue: Float? = null
    private lateinit var dh: DatabaseHelper
    private lateinit var latitudeDB: String
    private lateinit var longitudeDB: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        latitudeDB = ""
        longitudeDB = ""
        dh = DatabaseHelper(this)
        sp = getSharedPreferences("ZoomInfo", Context.MODE_PRIVATE)!!
        zoomValue = sp.getFloat("zoomFloat",1f)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
        } else {
            val intent = intent
            val info = intent.getStringExtra("info")
            if(info.equals("adapter")){

            }
            searchLocation(zoomValue!!)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            if(grantResults.isNotEmpty()){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    searchLocation(zoomValue!!)
                } else {
                    Toast.makeText(this,"Please Confirm Location Permission",Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun searchLocation(zoomValue: Float){
        val intent = intent
        val info = intent.getStringExtra("info")

        if(info.equals("manual")){
            val latitude = intent.getDoubleExtra("notrandomlatitude",0.0)
            val longitude = intent.getDoubleExtra("notrandomlongitude",0.0)
            val location = LatLng(latitude,longitude)
            latitudeDB = latitude.toString()
            longitudeDB = longitude.toString()
            mMap.clear()
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,zoomValue))
            val geocoder = Geocoder(this, Locale.getDefault())
            try {
                var address: String? = ""
                val addressList = geocoder.getFromLocation(location.latitude,location.longitude,1)
                if(addressList != null && addressList.size>0){
                    if(addressList[0].countryName != null){
                        address += addressList[0].countryName
                        if(addressList[0].thoroughfare != null){
                            address += " " + addressList[0].thoroughfare
                        }
                    }
                } else {
                    address = null
                }
                if(address != null){
                    mMap.addMarker(MarkerOptions().position(location).title(address))
                } else {
                    mMap.addMarker(MarkerOptions().position(location).title("Location"))
                }
            } catch (e: Exception){
                e.printStackTrace()
            }

        } else if(info.equals("random")){
            val latitude = intent.getDoubleExtra("randomlatitude",0.0)
            val longitude = intent.getDoubleExtra("randomlongitude",0.0)
            val location = LatLng(latitude,longitude)
            latitudeDB = latitude.toString()
            longitudeDB = longitude.toString()
            mMap.clear()
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,zoomValue))
            val geocoder = Geocoder(this, Locale.getDefault())
            try {
                var address: String? = ""
                val addressList = geocoder.getFromLocation(location.latitude,location.longitude,1)
                if(addressList != null && addressList.size>0){
                    if(addressList[0].countryName != null){
                        address += addressList[0].countryName
                        if(addressList[0].thoroughfare != null){
                            address += " " + addressList[0].thoroughfare
                        }
                    }
                } else {
                    address = null
                }
                if(address != null){
                    mMap.addMarker(MarkerOptions().position(location).title(address))
                } else {
                    mMap.addMarker(MarkerOptions().position(location).title("Location"))
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        } else if(info.equals("adapter")) {
            val latitude = intent.getDoubleExtra("adapterlatitude", 0.0)
            val longitude = intent.getDoubleExtra("adapterlongitude", 0.0)
            val location = LatLng(latitude, longitude)
            latitudeDB = latitude.toString()
            longitudeDB = longitude.toString()
            mMap.clear()
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomValue))
            val geocoder = Geocoder(this, Locale.getDefault())
            try {
                var address: String? = ""
                val addressList = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (addressList != null && addressList.size > 0) {
                    if (addressList[0].countryName != null) {
                        address += addressList[0].countryName
                        if (addressList[0].thoroughfare != null) {
                            address += " " + addressList[0].thoroughfare
                        }
                    }
                } else {
                    address = null
                }
                if (address != null) {
                    mMap.addMarker(MarkerOptions().position(location).title(address))
                } else {
                    mMap.addMarker(MarkerOptions().position(location).title("Location"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.map_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_save_menu -> {
                val builder = AlertDialog.Builder(this)
                val dialogBinding: MapSaveCustomDialogBinding = MapSaveCustomDialogBinding.inflate(layoutInflater)
                builder.setView(dialogBinding.root)
                val mAlertDialog = builder.show()

                dialogBinding.buttonAlertConfirm.setOnClickListener {
                    if(dialogBinding.editTextLocationName.text.isNotEmpty()){
                        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                            val description = dialogBinding.editTextLocationName.text.toString()
                            LocationDao().addLocation(dh,latitudeDB,longitudeDB,description)
                            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this,"Please Confirm Location Permission",Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this,"Please Enter Location Name",Toast.LENGTH_SHORT).show()
                    }
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