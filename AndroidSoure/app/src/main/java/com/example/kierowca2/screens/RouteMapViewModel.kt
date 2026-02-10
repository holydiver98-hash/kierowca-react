package com.example.kierowca2.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kierowca2.data.StopWithTime
import com.example.kierowca2.data.entity.ShapeDao
import com.example.kierowca2.data.entity.ShapePointEntity
import com.example.kierowca2.data.entity.TripDao
import com.example.kierowca2.data.gtfsDao

class RouteMapViewModel(
    private val tripDao: TripDao,
    private val shapeDao: ShapeDao,
    private val gtfsDao: gtfsDao
) : ViewModel() {

    private val _shape = MutableLiveData<List<ShapePointEntity>>()
    val shape: LiveData<List<ShapePointEntity>> = _shape

    private val _stops = MutableLiveData<List<StopWithTime>>()
    val stops: LiveData<List<StopWithTime>> = _stops

    suspend fun loadTrip(tripId: String) {
        val trip = tripDao.getTrip(tripId) ?: return
        val shapeId = trip.shapeId

        if (shapeId != null) {
            val shapePoints = shapeDao.getShapePoints(shapeId)
            _shape.postValue(shapePoints)
        }

        val stopsWithTime = gtfsDao.getStopsWithTimeForTrip(tripId)
        _stops.postValue(stopsWithTime)
    }
}

class RouteMapViewModelFactory(
    private val tripDao: TripDao,
    private val shapeDao: ShapeDao,
    private val gtfsDao: gtfsDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouteMapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RouteMapViewModel(tripDao, shapeDao, gtfsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
