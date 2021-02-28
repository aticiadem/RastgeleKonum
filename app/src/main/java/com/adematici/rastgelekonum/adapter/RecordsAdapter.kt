package com.adematici.rastgelekonum.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adematici.rastgelekonum.database.DatabaseHelper
import com.adematici.rastgelekonum.database.LocationDao
import com.adematici.rastgelekonum.databinding.RecyclerRowRecordsBinding
import com.adematici.rastgelekonum.model.LocationModel

class RecordsAdapter(private val mContext:Context,
                     private var locationArrayList: ArrayList<LocationModel>)
    : RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder>() {

    private val dh = DatabaseHelper(mContext)

    class RecordsViewHolder(val itemBinding: RecyclerRowRecordsBinding)
        : RecyclerView.ViewHolder(itemBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsViewHolder {
        val binding = RecyclerRowRecordsBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return RecordsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        holder.itemBinding.textViewDescription.text = locationArrayList[position].description
        holder.itemBinding.imageViewDelete.setOnClickListener {
            LocationDao().deleteLocation(dh,locationArrayList[position].locationId)
            locationArrayList = LocationDao().allLocationRecords(dh)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return locationArrayList.size
    }


}