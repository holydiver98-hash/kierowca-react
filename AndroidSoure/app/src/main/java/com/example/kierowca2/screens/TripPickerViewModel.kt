package com.example.kierowca2.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kierowca2.data.TripWithDetails
import com.example.kierowca2.data.gtfsDao
import kotlinx.coroutines.launch

class TripPickerViewModel(private val dao: gtfsDao) : ViewModel() {

    private val _trips = MutableLiveData<List<TripWithDetails>>()
    val trips: LiveData<List<TripWithDetails>> = _trips

    fun loadTrips(
        routeId: String?,
        directionId: Int?,
        variantId: String?,
        brigadeId: String?,
        vehicleId: String?,
        serviceId: String?
    ) {
        Log.d("TripPickerViewModel", "--- loadTrips Query Params ---")
        Log.d("TripPickerViewModel", "routeId=$routeId, dir=$directionId, variant=$variantId, brigade=$brigadeId, service=$serviceId")
        
        viewModelScope.launch {
            try {
                val list = dao.getTripsWithDetailsFiltered(routeId, directionId, variantId, brigadeId, vehicleId, serviceId)
                
                Log.d("TripPickerViewModel", "Found ${list.size} trips in DB")
                list.forEachIndexed { index, item ->
                    // Выводим все поля, включая variant_id, чтобы увидеть разницу
                    Log.d("TripPickerViewModel", "TRIP[$index]: ID=${item.trip.tripId} | Start=${item.startTime} | End=${item.endTime} | Variant=${item.trip.variantId} | Main=${item.variantIsMain}")
                }
                
                _trips.postValue(list)
            } catch (e: Exception) {
                Log.e("TripPickerViewModel", "Query Error", e)
            }
        }
    }
}

class TripPickerViewModelFactory(private val dao: gtfsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripPickerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TripPickerViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
