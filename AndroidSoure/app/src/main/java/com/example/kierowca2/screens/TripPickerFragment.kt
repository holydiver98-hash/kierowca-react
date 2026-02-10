package com.example.kierowca2.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kierowca2.R
import com.example.kierowca2.adapter.TripAdapter
import com.example.kierowca2.data.GtfsDatabase
import com.example.kierowca2.databinding.FragmentTripPickerBinding

class TripPickerFragment : Fragment() {

    private lateinit var binding: FragmentTripPickerBinding
    private lateinit var viewModel: TripPickerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTripPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        Log.d("savedInstanceState", "$savedInstanceState")
        val routeId = requireArguments().getString("route_id")
        val directionId = requireArguments().getInt("direction_id").let { if (it == -1) null else it }
        val variantId = requireArguments().getString("variant_id")
        val brigadeId = requireArguments().getString("brigade_id")
        val vehicleId = requireArguments().getString("vehicle_id")
        val serviceId = requireArguments().getString("service_id")


        val dao = GtfsDatabase.getInstance(requireContext()).gtfsDao()
        viewModel = ViewModelProvider(
            this,
            TripPickerViewModelFactory(dao)
        )[TripPickerViewModel::class.java]

        val adapter = TripAdapter { tripWithDetails ->
            val bundle = bundleOf("trip_id" to tripWithDetails.trip.tripId)
            findNavController().navigate(
                R.id.action_tripPicker_to_routeMapFragment,
                bundle
            )
        }

        binding.recyclerTrips.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTrips.adapter = adapter

        viewModel.trips.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            // Update toolbar title
            (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.found_routes, "${list.size}")
        }

        viewModel.loadTrips(routeId, directionId, variantId, brigadeId, vehicleId, serviceId)
    }
}
