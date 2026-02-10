package com.example.kierowca2

sealed class SyncState {
    object Idle : SyncState()
    object Loading : SyncState()
    data class Progress(val message: String) : SyncState()
    object Success : SyncState()
    data class Error(val error: String) : SyncState()
}

data class DirectionItem(
    val directionId: Int?,
    val headsign: String?
)

