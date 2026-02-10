package com.example.kierowca2.utils

import android.content.Context
import android.util.Log
import com.example.kierowca2.data.GtfsDatabase
import com.example.kierowca2.data.entity.AppLogEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.PrintWriter
import java.io.StringWriter

object AppLogger {
    private const val TAG = "AppLogger"

    fun logError(context: Context, message: String, throwable: Throwable? = null) {
        val stackTrace = throwable?.let {
            val sw = StringWriter()
            it.printStackTrace(PrintWriter(sw))
            sw.toString()
        }

        Log.e(TAG, message, throwable)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = GtfsDatabase.getInstance(context)
                val log = AppLogEntity(
                    message = message,
                    stackTrace = stackTrace
                )
                db.appLogDao().insert(log)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to write log to database", e)
            }
        }
    }
}
