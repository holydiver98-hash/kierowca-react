package com.example.kierowca2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kierowca2.R
import com.example.kierowca2.data.entity.AgencyEntity

class AgencyAdapter(
    private var items: List<AgencyEntity>,
    private val onChecked: (AgencyEntity, Boolean) -> Unit
) : RecyclerView.Adapter<AgencyAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val check: CheckBox = view.findViewById(R.id.checkboxAgency)
        val name: TextView = view.findViewById(R.id.textAgencyName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_agency, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.name.text = item.agencyName
        holder.check.isChecked = item.isEnabled

        holder.check.setOnCheckedChangeListener(null)
        holder.check.setOnCheckedChangeListener { _, checked ->
            onChecked(item, checked)
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(newItems: List<AgencyEntity>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
