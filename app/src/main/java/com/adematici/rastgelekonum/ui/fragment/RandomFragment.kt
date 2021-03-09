package com.adematici.rastgelekonum.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.adematici.rastgelekonum.MapsActivity
import com.adematici.rastgelekonum.R
import com.adematici.rastgelekonum.databinding.FragmentRandomBinding
import com.adematici.rastgelekonum.ui.activity.SettingsActivity

class RandomFragment : Fragment() {

    private lateinit var binding: FragmentRandomBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentRandomBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.buttonRandomLocation.setOnClickListener {
            //val latitude: Double = Random.nextDouble(-85.0000000,85.0000000)
            //val longitude: Double = Random.nextDouble(-180.0000000,180.0000000)
            //val intent = Intent(requireActivity(), MapsActivity::class.java)
            //intent.putExtra("info","random")
            //intent.putExtra("randomlatitude",latitude)
            //intent.putExtra("randomlongitude",longitude)
            //startActivity(intent)
            activity?.let {
                val intent = Intent(it, MapsActivity::class.java)
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
        }
        return true
    }

}