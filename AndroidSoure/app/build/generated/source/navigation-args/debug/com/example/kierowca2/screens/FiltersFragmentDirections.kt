package com.example.kierowca2.screens

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.kierowca2.R
import kotlin.Int
import kotlin.String

public class FiltersFragmentDirections private constructor() {
  private data class ActionFiltersToTripPicker(
    public val routeId: String,
    public val directionId: Int = -1,
    public val variantId: String? = "null",
    public val brigadeId: String? = "null",
    public val vehicleId: String? = "null",
    public val serviceId: String? = "null",
  ) : NavDirections {
    public override val actionId: Int = R.id.action_filters_to_tripPicker

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("route_id", this.routeId)
        result.putInt("direction_id", this.directionId)
        result.putString("variant_id", this.variantId)
        result.putString("brigade_id", this.brigadeId)
        result.putString("vehicle_id", this.vehicleId)
        result.putString("service_id", this.serviceId)
        return result
      }
  }

  public companion object {
    public fun actionFiltersToTripPicker(
      routeId: String,
      directionId: Int = -1,
      variantId: String? = "null",
      brigadeId: String? = "null",
      vehicleId: String? = "null",
      serviceId: String? = "null",
    ): NavDirections = ActionFiltersToTripPicker(routeId, directionId, variantId, brigadeId,
        vehicleId, serviceId)
  }
}
