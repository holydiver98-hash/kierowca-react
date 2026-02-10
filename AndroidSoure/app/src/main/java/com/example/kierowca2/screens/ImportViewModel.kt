package com.example.kierowca2.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kierowca2.GTFS_API_URL
import com.example.kierowca2.GTFS_URL
import com.example.kierowca2.SyncState
import com.example.kierowca2.data.GtfsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ImportViewModel(
    private val repository: GtfsRepository
) : ViewModel() {

    private val _state = MutableLiveData<SyncState>(SyncState.Idle)
    val state: LiveData<SyncState> = _state

    private var isSyncRunning = false

    fun startSync() {
        if (isSyncRunning) return
        isSyncRunning = true

        viewModelScope.launch {
            try {
                _state.postValue(SyncState.Loading)
                _state.postValue(SyncState.Progress("Получение ссылки на актуальные данные..."))

                val dynamicUrl = fetchLatestGtfsUrl()
                val urlToUse = dynamicUrl ?: GTFS_URL
                
                Log.d("ImportViewModel", "Using URL for import: $urlToUse")

                repository.importGtfs(
                    urlToUse,
                    onProgress = { msg -> _state.postValue(SyncState.Progress(msg)) }
                )

                _state.postValue(SyncState.Success)
            } catch (e: Exception) {
                Log.e("ImportViewModel", "Sync failed", e)
                _state.postValue(SyncState.Error(e.message ?: "Ошибка"))
            } finally {
                isSyncRunning = false
            }
        }
    }

    private suspend fun fetchLatestGtfsUrl(): String? = withContext(Dispatchers.IO) {
        try {
            val client = OkHttpClient()
            val request = Request.Builder().url(GTFS_API_URL).build()
            val response = client.newCall(request).execute()
            
            if (!response.isSuccessful) return@withContext null
            
            val jsonData = response.body?.string() ?: return@withContext null
            val root = JSONObject(jsonData)
            val result = root.optJSONObject("result") ?: return@withContext null
            val resources = result.optJSONArray("resources") ?: return@withContext null
            
            var latestUrl: String? = null
            var lastCreated: String = ""

            for (i in 0 until resources.length()) {
                val res = resources.getJSONObject(i)
                val name = res.optString("name", "")
                val url = res.optString("url", "")
                val created = res.optString("created", "") // Формат: 2024-01-25T...

                // Ищем файл, имя которого содержит GTFS
                if (name.contains("GTFS", ignoreCase = true) && url.endsWith(".zip")) {
                    // Сравниваем даты создания, чтобы взять самый свежий
                    if (created > lastCreated) {
                        lastCreated = created
                        latestUrl = url
                    }
                }
            }
            latestUrl
        } catch (e: Exception) {
            Log.e("ImportViewModel", "Error fetching dynamic URL", e)
            null
        }
    }
}

class ImportViewModelFactory(private val repository: GtfsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImportViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImportViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
