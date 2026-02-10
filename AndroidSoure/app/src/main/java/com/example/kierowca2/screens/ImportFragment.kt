package com.example.kierowca2.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kierowca2.R
import com.example.kierowca2.SyncState
import com.example.kierowca2.data.GtfsDatabase
import com.example.kierowca2.data.GtfsRepository

class ImportFragment : Fragment() {

    private lateinit var viewModel: ImportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_import, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn = view.findViewById<Button>(R.id.btnSync)
        val progress = view.findViewById<ProgressBar>(R.id.progress)
        val status = view.findViewById<TextView>(R.id.txtStatus)

        val db = GtfsDatabase.getInstance(requireContext())
        val repo = GtfsRepository(requireContext(), db)
        val factory = ImportViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[ImportViewModel::class.java]

        btn.setOnClickListener {
            viewModel.startSync() // Убрал аргумент
        }

        viewModel.state.observe(viewLifecycleOwner) { s ->
            when (s) {
                is SyncState.Idle -> {
                    progress.visibility = View.GONE
                    btn.isEnabled = true
                    status.text = ""
                }

                is SyncState.Loading -> {
                    progress.visibility = View.VISIBLE
                    btn.isEnabled = false
                    status.text = "Начинаю загрузку..."
                }

                is SyncState.Progress -> {
                    progress.visibility = View.VISIBLE
                    btn.isEnabled = false
                    status.text = s.message
                }

                is SyncState.Success -> {
                    progress.visibility = View.GONE
                    btn.isEnabled = true
                    status.text = "Готово!"
                }

                is SyncState.Error -> {
                    progress.visibility = View.GONE
                    btn.isEnabled = true
                    status.text = "Ошибка: ${s.error}"
                }
            }
        }
    }
}
