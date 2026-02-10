package com.example.kierowca2.screens

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class TripPickerFragmentArgs(
  public val routeId: String,
  public val directionId: Int = -1,
  public val variantId: String? = "null",
  public val brigadeId: String? = "null",
  public val vehicleId: String? = "null",
  public val serviceId: String? = "null",
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("route_id", this.routeId)
    result.putInt("direction_id", this.directionId)
    result.putString("variant_id", this.variantId)
    result.putString("brigade_id", this.brigadeId)
    result.putString("vehicle_id", this.vehicleId)
    result.putString("service_id", this.serviceId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("route_id", this.routeId)
    result.set("direction_id", this.directionId)
    result.set("variant_id", this.variantId)
    result.set("brigade_id", this.brigadeId)
    result.set("vehicle_id", this.vehicleId)
    result.set("service_id", this.serviceId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): TripPickerFragmentArgs {
      bundle.setClassLoader(TripPickerFragmentArgs::class.java.classLoader)
      val __routeId : String?
      if (bundle.containsKey("route_id")) {
        __routeId = bundle.getString("route_id")
        if (__routeId == null) {
          throw IllegalArgumentException("Argument \"route_id\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"route_id\" is missing and does not have an android:defaultValue")
      }
      val __directionId : Int
      if (bundle.containsKey("direction_id")) {
        __directionId = bundle.getInt("direction_id")
      } else {
        __directionId = -1
      }
      val __variantId : String?
      if (bundle.containsKey("variant_id")) {
        __variantId = bundle.getString("variant_id")
      } else {
        __variantId = "null"
      }
      val __brigadeId : String?
      if (bundle.containsKey("brigade_id")) {
        __brigadeId = bundle.getString("brigade_id")
      } else {
        __brigadeId = "null"
      }
      val __vehicleId : String?
      if (bundle.containsKey("vehicle_id")) {
        __vehicleId = bundle.getString("vehicle_id")
      } else {
        __vehicleId = "null"
      }
      val __serviceId : String?
      if (bundle.containsKey("service_id")) {
        __serviceId = bundle.getString("service_id")
      } else {
        __serviceId = "null"
      }
      return TripPickerFragmentArgs(__routeId, __directionId, __variantId, __brigadeId, __vehicleId,
          __serviceId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): TripPickerFragmentArgs {
      val __routeId : String?
      if (savedStateHandle.contains("route_id")) {
        __routeId = savedStateHandle["route_id"]
        if (__routeId == null) {
          throw IllegalArgumentException("Argument \"route_id\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"route_id\" is missing and does not have an android:defaultValue")
      }
      val __directionId : Int?
      if (savedStateHandle.contains("direction_id")) {
        __directionId = savedStateHandle["direction_id"]
        if (__directionId == null) {
          throw IllegalArgumentException("Argument \"direction_id\" of type integer does not support null values")
        }
      } else {
        __directionId = -1
      }
      val __variantId : String?
      if (savedStateHandle.contains("variant_id")) {
        __variantId = savedStateHandle["variant_id"]
      } else {
        __variantId = "null"
      }
      val __brigadeId : String?
      if (savedStateHandle.contains("brigade_id")) {
        __brigadeId = savedStateHandle["brigade_id"]
      } else {
        __brigadeId = "null"
      }
      val __vehicleId : String?
      if (savedStateHandle.contains("vehicle_id")) {
        __vehicleId = savedStateHandle["vehicle_id"]
      } else {
        __vehicleId = "null"
      }
      val __serviceId : String?
      if (savedStateHandle.contains("service_id")) {
        __serviceId = savedStateHandle["service_id"]
      } else {
        __serviceId = "null"
      }
      return TripPickerFragmentArgs(__routeId, __directionId, __variantId, __brigadeId, __vehicleId,
          __serviceId)
    }
  }
}
