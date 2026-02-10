package com.example.kierowca2.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kierowca2.data.gtfsDao
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainViewModel(private val dao: gtfsDao) : ViewModel() {

    private val _isDataOutdated = MutableLiveData<Boolean>()
    val isDataOutdated: LiveData<Boolean> = _isDataOutdated

    init {
        checkDataRelevance()
    }

    private fun checkDataRelevance() {
        viewModelScope.launch {
            try {
                val dateRange = dao.getCalendarDateRange()
                if (dateRange?.minDate == null || dateRange.maxDate == null) {
                    _isDataOutdated.postValue(true)
                    return@launch
                }

                val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
                val today = LocalDate.now()
                val minDate = LocalDate.parse(dateRange.minDate, formatter)
                val maxDate = LocalDate.parse(dateRange.maxDate, formatter)

                if (today.isBefore(minDate) || today.isAfter(maxDate)) {
                    _isDataOutdated.postValue(true)
                }
            } catch (e: Exception) {
                // Handle potential parsing errors or DB errors
                _isDataOutdated.postValue(true)
            }
        }
    }
}

class MainViewModelFactory(private val dao: gtfsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
