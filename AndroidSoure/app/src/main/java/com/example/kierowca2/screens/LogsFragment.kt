package com.example.kierowca2.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kierowca2.R
import com.example.kierowca2.adapter.LogsAdapter
import com.example.kierowca2.data.AppLogDao
import com.example.kierowca2.data.GtfsDatabase

class LogsFragment : Fragment() {

    private lateinit var viewModel: LogsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = GtfsDatabase.getInstance(requireContext())
        viewModel = ViewModelProvider(this, LogsViewModelFactory(db.appLogDao()))[LogsViewModel::class.java]

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerLogs)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = LogsAdapter()
        recycler.adapter = adapter

        viewModel.logs.observe(viewLifecycleOwner) { logs ->
            adapter.submitList(logs)
        }

        view.findViewById<Button>(R.id.btnClearLogs).setOnClickListener {
            viewModel.clearLogs()
        }
    }
}

class LogsViewModelFactory(private val dao: AppLogDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
