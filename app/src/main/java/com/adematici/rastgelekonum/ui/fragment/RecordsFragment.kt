package com.adematici.rastgelekonum.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.adapter.RecordsAdapter
import com.adematici.rastgelekonum.database.DatabaseHelper
import com.adematici.rastgelekonum.database.LocationDao
import com.adematici.rastgelekonum.databinding.FragmentRecordsBinding
import com.adematici.rastgelekonum.model.LocationModel

class RecordsFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var allLocations: ArrayList<LocationModel>

    private lateinit var binding: FragmentRecordsBinding
    private lateinit var locationList: ArrayList<LocationModel>
    private lateinit var adapter: RecordsAdapter
    private lateinit var dh: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecordsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        dh = DatabaseHelper(requireActivity())

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true

        binding.recyclerView.layoutManager = layoutManager

        locationList = LocationDao().allLocationRecords(dh)
        adapter = RecordsAdapter(requireActivity(),locationList)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.records_menu,menu)

        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                return true
            }
            R.id.action_delete -> {
                val liste = LocationDao().allLocationRecords(dh)
                if(liste.isNotEmpty()){
                    LocationDao().deleteAllLocation(dh)
                    locationList = LocationDao().allLocationRecords(dh)
                    adapter = RecordsAdapter(requireActivity(),locationList)
                    binding.recyclerView.adapter = adapter
                    Toast.makeText(activity,R.string.records_deleted_all,Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity,R.string.records_not_deleted,Toast.LENGTH_SHORT).show()
                }
                return true
            }
            R.id.action_refresh -> {
                locationList = LocationDao().allLocationRecords(dh)
                adapter = RecordsAdapter(requireActivity(),locationList)
                binding.recyclerView.adapter = adapter
                return true
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchLocation(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchLocation(newText!!)
        return true
    }

    private fun searchLocation(search: String){
        allLocations = LocationDao().searchLocation(dh,search)
        adapter = RecordsAdapter(requireActivity(),allLocations)
        binding.recyclerView.adapter = adapter
    }

}