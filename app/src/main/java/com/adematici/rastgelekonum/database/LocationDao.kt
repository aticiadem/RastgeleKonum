package com.adematici.rastgelekonum.database

import android.content.ContentValues
import com.adematici.rastgelekonum.model.LocationModel

class LocationDao {

    fun addLocation(dh: DatabaseHelper,
                    locationLatitude: String,
                    locationLongitude: String,
                    locationDescription: String){
        val db = dh.writableDatabase
        val values = ContentValues()

        values.put("location_latitude",locationLatitude)
        values.put("location_longitude",locationLongitude)
        values.put("location_description",locationDescription)

        db.insertOrThrow("records",null,values)
        db.close()
    }

    fun allLocationRecords(dh: DatabaseHelper): ArrayList<LocationModel>{
        val db = dh.writableDatabase
        val locationArrayList = ArrayList<LocationModel>()
        val cursor = db.rawQuery("SELECT * FROM records",null)

        while (cursor.moveToNext()){
            val location = LocationModel(
                    cursor.getInt(cursor.getColumnIndex("location_id")),
                    cursor.getString(cursor.getColumnIndex("location_latitude")),
                    cursor.getString(cursor.getColumnIndex("location_longitude")),
                    cursor.getString(cursor.getColumnIndex("location_description")))
            locationArrayList.add(location)
        }
        return locationArrayList
    }

    fun deleteLocation(dh: DatabaseHelper, locationId: Int){
        val db = dh.writableDatabase
        db.delete("records","location_id", arrayOf(locationId.toString()))
        db.close()
    }

    fun deleteAllLocation(dh: DatabaseHelper){
        val db = dh.writableDatabase
        db.delete("records",null,null)
        db.close()
    }

    fun searchLocation(dh: DatabaseHelper, search: String): ArrayList<LocationModel>{
        val db = dh.writableDatabase
        val resultArrayList = ArrayList<LocationModel>()
        val c = db.rawQuery("SELECT * FROM records WHERE location_description like '%$search%'",null)

        while (c.moveToNext()){
            val result = LocationModel(
                    c.getInt(c.getColumnIndex("location_id")),
                    c.getString(c.getColumnIndex("location_latitude")),
                    c.getString(c.getColumnIndex("location_longitude")),
                    c.getString(c.getColumnIndex("location_description")))
            resultArrayList.add(result)
        }
        return resultArrayList
    }

}