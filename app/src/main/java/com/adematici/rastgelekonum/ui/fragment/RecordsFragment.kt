package com.adematici.rastgelekonum.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.adapter.RecordsAdapter
import com.adematici.rastgelekonum.databinding.FragmentRecordsBinding
import com.adematici.rastgelekonum.model.LocationModel

class RecordsFragment : Fragment() {

    private lateinit var binding: FragmentRecordsBinding
    private lateinit var locationList: ArrayList<LocationModel>
    private lateinit var adapter: RecordsAdapter

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

        locationList = ArrayList()

        adapter = RecordsAdapter(requireActivity(),locationList)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.records_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                return true
            }
            R.id.action_delete -> {
                return true
            }
        }
        return true
    }

}