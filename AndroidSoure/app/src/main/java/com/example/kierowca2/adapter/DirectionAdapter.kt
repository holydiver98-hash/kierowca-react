package com.example.kierowca2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kierowca2.DirectionItem
import com.example.kierowca2.R

class DirectionAdapter(
    private var items: List<DirectionItem>,
    private val onClick: (DirectionItem) -> Unit
) : RecyclerView.Adapter<DirectionAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textDirection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_direction, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.text.text = "Direction ${item.directionId}: ${item.headsign}"
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size

    fun update(list: List<DirectionItem>) {
        items = list
        notifyDataSetChanged()
    }
}
