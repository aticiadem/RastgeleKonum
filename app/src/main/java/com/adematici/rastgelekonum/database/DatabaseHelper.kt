package com.adematici.rastgelekonum.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(mContext: Context): SQLiteOpenHelper(mContext,"Records",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE records(location_id INTEGER PRIMARY KEY AUTOINCREMENT,location_latitude TEXT,location_longitude TEXT,location_description TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS records")
    }
}