package com.example.kierowca2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kierowca2.R
import com.example.kierowca2.data.entity.AppLogEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogsAdapter : ListAdapter<AppLogEntity, LogsAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<AppLogEntity>() {
        override fun areItemsTheSame(old: AppLogEntity, newItem: AppLogEntity) = old.id == newItem.id
        override fun areContentsTheSame(old: AppLogEntity, newItem: AppLogEntity) = old == newItem
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val txtTimestamp: TextView = view.findViewById(R.id.txtTimestamp)
        val txtMessage: TextView = view.findViewById(R.id.txtMessage)
        val txtStackTrace: TextView = view.findViewById(R.id.txtStackTrace)

        fun bind(item: AppLogEntity) {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            txtTimestamp.text = sdf.format(Date(item.timestamp))
            txtMessage.text = item.message
            
            if (!item.stackTrace.isNullOrEmpty()) {
                txtStackTrace.text = item.stackTrace
                txtStackTrace.visibility = View.GONE // Initially hidden
                
                itemView.setOnClickListener {
                    if (txtStackTrace.visibility == View.VISIBLE) {
                        txtStackTrace.visibility = View.GONE
                    } else {
                        txtStackTrace.visibility = View.VISIBLE
                    }
                }
            } else {
                txtStackTrace.visibility = View.GONE
                itemView.setOnClickListener(null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_log, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
