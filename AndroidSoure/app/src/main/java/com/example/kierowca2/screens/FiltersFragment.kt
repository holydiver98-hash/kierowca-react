package com.example.kierowca2.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kierowca2.R
import com.example.kierowca2.data.GtfsDatabase
import com.example.kierowca2.data.entity.RouteEntity
import com.example.kierowca2.data.entity.RouteTypeEntity
import com.example.kierowca2.data.gtfsDao
import java.time.DayOfWeek

class FiltersFragment : Fragment() {

    private lateinit var vm: FiltersViewModel
    private lateinit var dao: gtfsDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_filters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dao = GtfsDatabase.getInstance(requireContext()).gtfsDao()
        vm = ViewModelProvider(this, FiltersViewModelFactory(dao))[FiltersViewModel::class.java]

        // --- Menu ---
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_route_picker, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_import -> {
                        findNavController().navigate(R.id.importFragment)
                        true
                    }
                    R.id.menu_settings -> {
                        findNavController().navigate(R.id.settingsFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val spAgency: Spinner = view.findViewById(R.id.spinnerAgency)
        val spRouteType: Spinner = view.findViewById(R.id.spinnerRouteType)
        val spRoute: Spinner = view.findViewById(R.id.spinnerRoute)
        val spBrigade: Spinner = view.findViewById(R.id.spinnerBrigade)
        val spDayOfWeek: Spinner = view.findViewById(R.id.spinnerDayOfWeek)
        val btnShow: Button = view.findViewById(R.id.btnShowTrips)

        // --- Agency ---
        vm.agencies.observe(viewLifecycleOwner) { list ->
            val names = list.map { it.agencyName ?: it.agencyId }
            val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, names)
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spAgency.adapter = adapter
        }

        // --- Route Type ---
        vm.routeTypes.observe(viewLifecycleOwner) { types ->
            val allOption = RouteTypeEntity("", "All") 
            val typeList = listOf(allOption) + types
            val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, typeList.map { it.routeType2Name })
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spRouteType.adapter = adapter
            spRouteType.tag = typeList

            vm.selectedRouteType.value?.let {
                val index = typeList.indexOfFirst { (id, _) -> id == it }
                if (index != -1) spRouteType.setSelection(index)
            }
        }

        spRouteType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val types = spRouteType.tag as? List<RouteTypeEntity> ?: return
                val selectedId = types.getOrNull(position)?.routeType2Id
                vm.selectRouteType(if (selectedId.isNullOrEmpty()) null else selectedId)
            }
        }

        // --- Route ---
        vm.routes.observe(viewLifecycleOwner) { routes ->
            val labels = routes.map { "${it.routeShortName ?: it.routeId} — ${it.routeLongName ?: ""}" }
            val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, labels)
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spRoute.adapter = adapter
            spRoute.tag = routes
            vm.selectedRoute.value?.let { selectedId ->
                val index = routes.indexOfFirst { it.routeId == selectedId }
                if (index != -1) spRoute.setSelection(index)
            }
        }
        spRoute.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val routes = spRoute.tag as? List<RouteEntity> ?: return
                vm.selectRoute(routes.getOrNull(position)?.routeId)
            }
        }

        // --- Brigade ---
        vm.brigades.observe(viewLifecycleOwner) { brigades ->
            val list = listOf("All") + brigades.map { it ?: "null" }
            val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spBrigade.adapter = adapter
            spBrigade.tag = brigades
            vm.selectedBrigade.value.let { selected ->
                val index = if (selected == null) 0 else brigades.indexOf(selected) + 1
                spBrigade.setSelection(index.coerceAtLeast(0))
            }
        }
        spBrigade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val brigades = spBrigade.tag as? List<String?> ?: return
                vm.selectBrigade(if (position == 0) null else brigades.getOrNull(position - 1))
            }
        }

        // --- Day of Week ---
        val dayNames = vm.availableDaysOfWeek.map { it.name.lowercase().replaceFirstChar { char -> char.uppercase() } }
        val dayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, dayNames)
        dayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spDayOfWeek.adapter = dayAdapter

        vm.selectedDayOfWeek.observe(viewLifecycleOwner) { selectedDay ->
            val index = vm.availableDaysOfWeek.indexOf(selectedDay)
            if (index != -1 && spDayOfWeek.selectedItemPosition != index) {
                spDayOfWeek.setSelection(index)
            }
        }

        spDayOfWeek.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                vm.selectDayOfWeek(vm.availableDaysOfWeek[position])
            }
        }

        // --- Button ---
        btnShow.setOnClickListener {
            val routeId = vm.selectedRoute.value ?: run {
                return@setOnClickListener
            }
            
            val bundle = bundleOf(
                "route_id" to routeId,
                "direction_id" to -1, // No longer filtered by direction
                "variant_id" to null,
                "brigade_id" to vm.selectedBrigade.value,
                "vehicle_id" to null,
                "service_id" to vm.selectedService.value
            )
            findNavController().navigate(R.id.action_filters_to_tripPicker, bundle)
        }
    }
}

class FiltersViewModelFactory(private val dao: gtfsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FiltersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FiltersViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
