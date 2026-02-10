package com.example.kierowca2.screens

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.kierowca2.R
import kotlin.Int
import kotlin.String

public class TripPickerFragmentDirections private constructor() {
  private data class ActionTripPickerToRouteMapFragment(
    public val tripId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_tripPicker_to_routeMapFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("trip_id", this.tripId)
        return result
      }
  }

  public companion object {
    public fun actionTripPickerToRouteMapFragment(tripId: String): NavDirections =
        ActionTripPickerToRouteMapFragment(tripId)
  }
}
