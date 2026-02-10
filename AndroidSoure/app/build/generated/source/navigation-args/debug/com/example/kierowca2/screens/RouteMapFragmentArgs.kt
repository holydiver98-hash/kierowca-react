package com.example.kierowca2.screens

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class RouteMapFragmentArgs(
  public val tripId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("trip_id", this.tripId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("trip_id", this.tripId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): RouteMapFragmentArgs {
      bundle.setClassLoader(RouteMapFragmentArgs::class.java.classLoader)
      val __tripId : String?
      if (bundle.containsKey("trip_id")) {
        __tripId = bundle.getString("trip_id")
        if (__tripId == null) {
          throw IllegalArgumentException("Argument \"trip_id\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"trip_id\" is missing and does not have an android:defaultValue")
      }
      return RouteMapFragmentArgs(__tripId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): RouteMapFragmentArgs {
      val __tripId : String?
      if (savedStateHandle.contains("trip_id")) {
        __tripId = savedStateHandle["trip_id"]
        if (__tripId == null) {
          throw IllegalArgumentException("Argument \"trip_id\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"trip_id\" is missing and does not have an android:defaultValue")
      }
      return RouteMapFragmentArgs(__tripId)
    }
  }
}
