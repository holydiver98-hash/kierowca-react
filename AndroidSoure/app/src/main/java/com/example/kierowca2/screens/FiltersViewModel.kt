package com.example.kierowca2.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.kierowca2.DirectionItem
import com.example.kierowca2.VehicleItem
import com.example.kierowca2.data.entity.AgencyEntity
import com.example.kierowca2.data.entity.RouteEntity
import com.example.kierowca2.data.entity.RouteTypeEntity
import com.example.kierowca2.data.entity.TripEntity
import com.example.kierowca2.data.gtfsDao
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FiltersViewModel(private val dao: gtfsDao) : ViewModel() {

    // Triggers for route type
    private val _selectedRouteType = MutableLiveData<String?>(null)
    val selectedRouteType: LiveData<String?> = _selectedRouteType

    // Selected values
    val selectedRoute = MutableLiveData<String?>(null)
    val selectedDirection = MutableLiveData<Int?>(null)
    val selectedVariant = MutableLiveData<String?>(null)
    val selectedBrigade = MutableLiveData<String?>(null)
    val selectedVehicle = MutableLiveData<String?>(null)
    
    private val _selectedService = MutableLiveData<String?>(null)
    val selectedService: LiveData<String?> = _selectedService

    private val _selectedDayOfWeek = MutableLiveData<DayOfWeek>(LocalDate.now().dayOfWeek)
    val selectedDayOfWeek: LiveData<DayOfWeek> = _selectedDayOfWeek

    val availableDaysOfWeek = DayOfWeek.values().toList()

    // Result list
    private val _trips = MutableLiveData<List<TripEntity>>()
    val trips: LiveData<List<TripEntity>> = _trips

    // LiveData for spinners
    val agencies: LiveData<List<AgencyEntity>> = dao.getSelectedAgenciesFlow().asLiveData()
    val routeTypes: LiveData<List<RouteTypeEntity>> = dao.getAvailableRouteTypes().asLiveData()

    val routes: LiveData<List<RouteEntity>> = _selectedRouteType.switchMap {
        dao.getRoutesForSelectedAgenciesFlow(it).asLiveData()
    }

    // New: Brigades depend directly on selectedRoute
    val brigades: LiveData<List<String?>> = selectedRoute.switchMap { routeId ->
        if (routeId == null) MutableLiveData(emptyList()) 
        else dao.getBrigadesFlow(routeId, null, null).asLiveData()
    }

    // Directions, Variants, Vehicles are currently not used in UI but kept for compatibility or future use
    val directions: LiveData<List<DirectionItem>> = selectedRoute.switchMap { routeId ->
        if (routeId == null) MutableLiveData(emptyList()) else dao.getDirectionsForRouteFlow(routeId).asLiveData()
    }

    val variants: LiveData<List<String?>> = selectedDirection.switchMap { directionId ->
        val routeId = selectedRoute.value
        if (routeId == null) MutableLiveData(emptyList()) else dao.getVariantsForRouteAndDirectionFlow(routeId, directionId).asLiveData()
    }

    val vehicles: LiveData<List<VehicleItem>> = selectedBrigade.switchMap { brigadeId ->
        val routeId = selectedRoute.value
        val dirId = selectedDirection.value
        val varId = selectedVariant.value
        if (routeId == null) MutableLiveData(emptyList()) else dao.getVehiclesFlow(routeId, dirId, varId, brigadeId).asLiveData()
    }

    init {
        updateAutomaticServiceId()
    }

    fun selectDayOfWeek(day: DayOfWeek) {
        if (_selectedDayOfWeek.value != day) {
            _selectedDayOfWeek.value = day
            updateAutomaticServiceId()
        }
    }

    private fun updateAutomaticServiceId() {
        viewModelScope.launch {
            val today = LocalDate.now()
            val dayOfWeek = _selectedDayOfWeek.value ?: today.dayOfWeek

            val dateInt = today.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()

            val all = dao.getAllCalendar()

            // 1. Try to find a match with date and day of week
            var matching = all.firstOrNull { row ->
                val startDate = row.startDate?.trim()?.toIntOrNull() ?: 0
                val endDate = row.endDate?.trim()?.toIntOrNull() ?: 0
                val isDateInRange = dateInt in startDate..endDate
                val isDayMatch = when (dayOfWeek) {
                    DayOfWeek.MONDAY -> row.monday == 1
                    DayOfWeek.TUESDAY -> row.tuesday == 1
                    DayOfWeek.WEDNESDAY -> row.wednesday == 1
                    DayOfWeek.THURSDAY -> row.thursday == 1
                    DayOfWeek.FRIDAY -> row.friday == 1
                    DayOfWeek.SATURDAY -> row.saturday == 1
                    DayOfWeek.SUNDAY -> row.sunday == 1
                }
                isDateInRange && isDayMatch
            }

            // 2. If no match, fallback to day of week only
            if (matching == null) {
                Log.d("FiltersViewModel", "No exact match found for $dayOfWeek. Falling back to day of week only.")
                matching = all.firstOrNull { row ->
                    when (dayOfWeek) {
                        DayOfWeek.MONDAY -> row.monday == 1
                        DayOfWeek.TUESDAY -> row.tuesday == 1
                        DayOfWeek.WEDNESDAY -> row.wednesday == 1
                        DayOfWeek.THURSDAY -> row.thursday == 1
                        DayOfWeek.FRIDAY -> row.friday == 1
                        DayOfWeek.SATURDAY -> row.saturday == 1
                        DayOfWeek.SUNDAY -> row.sunday == 1
                    }
                }
            }

            _selectedService.value = matching?.serviceId
        }
    }

    // Setters
    fun selectRouteType(typeId: String?) {
        if (_selectedRouteType.value != typeId) {
            _selectedRouteType.value = typeId
            selectRoute(null)
        }
    }

    fun selectRoute(routeId: String?) {
        if (selectedRoute.value != routeId) {
            selectedRoute.value = routeId
            selectBrigade(null) // Reset brigade when route changes
        }
    }

    fun selectDirection(directionId: Int?) {
        if (selectedDirection.value != directionId) {
            selectedDirection.value = directionId
            selectVariant(null)
        }
    }

    fun selectVariant(variantId: String?) {
        if (selectedVariant.value != variantId) {
            selectedVariant.value = variantId
            selectBrigade(null)
        }
    }

    fun selectBrigade(brigadeId: String?) {
        if (selectedBrigade.value != brigadeId) {
            selectedBrigade.value = brigadeId
            selectVehicle(null)
        }
    }

    fun selectVehicle(vehicleId: String?) {
        if (selectedVehicle.value != vehicleId) {
            selectedVehicle.value = vehicleId
        }
    }

    fun searchTrips() {
        viewModelScope.launch {
            val route = selectedRoute.value
            val direction = selectedDirection.value
            val variant = selectedVariant.value
            val brigade = selectedBrigade.value
            val vehicle = selectedVehicle.value
            val service = selectedService.value

            val list = dao.getTripsFiltered(route, direction, variant, brigade, vehicle, service)
            _trips.value = list
        }
    }
}
