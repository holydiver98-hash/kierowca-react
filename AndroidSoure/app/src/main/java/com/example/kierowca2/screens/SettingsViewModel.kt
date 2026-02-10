package com.example.kierowca2.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.kierowca2.data.DateRange
import com.example.kierowca2.data.entity.AgencyEntity
import com.example.kierowca2.data.gtfsDao
import kotlinx.coroutines.launch

class SettingsViewModel(private val dao: gtfsDao) : ViewModel() {

    val agencies: LiveData<List<AgencyEntity>> = dao.getAllAgencies().asLiveData()
    val dateRange: LiveData<DateRange?> = liveData { emit(dao.getCalendarDateRange()) }

    fun updateAgency(agency: AgencyEntity, isEnabled: Boolean) {
        viewModelScope.launch {
            dao.updateAgency(agency.copy(isEnabled = isEnabled))
        }
    }
}

