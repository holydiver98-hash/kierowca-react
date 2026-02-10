package com.example.kierowca2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kierowca2.data.TripWithDetails
import com.example.kierowca2.databinding.ItemTripBinding

class TripAdapter(
    private val onClick: (TripWithDetails) -> Unit
) : ListAdapter<TripWithDetails, TripAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<TripWithDetails>() {
        override fun areItemsTheSame(old: TripWithDetails, newItem: TripWithDetails) =
            old.trip.tripId == newItem.trip.tripId

        override fun areContentsTheSame(old: TripWithDetails, newItem: TripWithDetails) =
            old == newItem
    }

    inner class VH(val binding: ItemTripBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TripWithDetails, nextItem: TripWithDetails?) {
            binding.txtTripId.text = item.trip.tripHeadsign
            binding.txtBrigade.text = "Brigade: ${item.trip.brigadeId ?: "-"}"
            binding.txtVehicle.text = "Vehicle: ${item.trip.vehicleId ?: "-"}"
            
            val displayStart = formatTime(item.startTime)
            val displayEnd = formatTime(item.endTime)
            binding.txtTime.text = "$displayStart - $displayEnd"
            
            binding.txtVehicleType.text = item.vehicleTypeName ?: "Unknown type"

            // Calculate and show layover (постой)
            if (nextItem != null && !item.endTime.isNullOrBlank() && !nextItem.startTime.isNullOrBlank()) {
                val currentEndMin = timeToMinutes(item.endTime)
                val nextStartMin = timeToMinutes(nextItem.startTime)
                val layover = nextStartMin - currentEndMin
                
                if (layover >= 0) {
                    binding.txtLayover.text = "$layover min"
                    binding.txtLayover.visibility = View.VISIBLE
                } else {
                    binding.txtLayover.visibility = View.GONE
                }
            } else {
                binding.txtLayover.visibility = View.GONE
            }

            // Show variant ID if not main
            if (item.variantIsMain == 0) {
                binding.txtVariant.visibility = View.VISIBLE
                binding.txtVariant.text = "Var: ${item.trip.variantId}"
            } else {
                binding.txtVariant.visibility = View.GONE
            }

            binding.root.setOnClickListener { onClick(item) }
        }

        private fun formatTime(time: String?): String {
            if (time == null) return "??"
            return try {
                val parts = time.split(":")
                val hours = parts[0].toInt() % 24
                val minutes = parts[1].toInt()
                String.format("%02d:%02d", hours, minutes)
            } catch (e: Exception) {
                time ?: "??"
            }
        }

        private fun timeToMinutes(time: String): Int {
            return try {
                val parts = time.split(":")
                val hours = parts[0].toInt()
                val minutes = parts[1].toInt()
                hours * 60 + minutes
            } catch (e: Exception) {
                0
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTripBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val current = getItem(position)
        val next = if (position + 1 < itemCount) getItem(position + 1) else null
        holder.bind(current, next)
    }
}
