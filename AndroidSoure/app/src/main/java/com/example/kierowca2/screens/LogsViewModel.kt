package com.example.kierowca2.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.kierowca2.data.AppLogDao
import com.example.kierowca2.data.entity.AppLogEntity
import kotlinx.coroutines.launch

class LogsViewModel(private val dao: AppLogDao) : ViewModel() {
    val logs: LiveData<List<AppLogEntity>> = dao.getAllLogs().asLiveData()

    fun clearLogs() {
        viewModelScope.launch {
            dao.clearLogs()
        }
    }
}
