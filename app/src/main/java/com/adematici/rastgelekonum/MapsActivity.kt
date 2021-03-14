package com.adematici.rastgelekonum

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        sp = getSharedPreferences("ZoomInfo", Context.MODE_PRIVATE)!!
        zoomValue = sp.getFloat("zoomFloat",1f)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
        } else {
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
        }
    }

}