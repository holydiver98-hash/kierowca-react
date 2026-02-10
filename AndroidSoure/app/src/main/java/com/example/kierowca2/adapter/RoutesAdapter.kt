package com.example.kierowca2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kierowca2.data.entity.RouteEntity

class RoutesAdapter(
    private val onClick: (RouteEntity) -> Unit
) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    private val items = mutableListOf<RouteEntity>()

    fun submitList(list: List<RouteEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.text.text = item.routeShortName
        holder.view.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size
}
