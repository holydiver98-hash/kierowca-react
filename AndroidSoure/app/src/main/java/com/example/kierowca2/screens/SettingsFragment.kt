package com.example.kierowca2.screens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kierowca2.R
import com.example.kierowca2.adapter.AgencyAdapter
import com.example.kierowca2.data.GtfsDatabase
import com.example.kierowca2.data.gtfsDao
import com.example.kierowca2.utils.AppLogger
import com.google.android.material.switchmaterial.SwitchMaterial
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

class SettingsFragment : Fragment() {

    private lateinit var vm: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = GtfsDatabase.getInstance(requireContext()).gtfsDao()
        vm = ViewModelProvider(this, SettingsViewModelFactory(dao))[SettingsViewModel::class.java]
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // --- Data Relevance --- 
        val txtDataRelevance = view.findViewById<TextView>(R.id.txtDataRelevance)
        vm.dateRange.observe(viewLifecycleOwner) {
            if (it?.minDate != null && it.maxDate != null) {
                val min = LocalDate.parse(it.minDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
                val max = LocalDate.parse(it.maxDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
                val formattedMin = min.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                val formattedMax = max.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                txtDataRelevance.text = getString(R.string.data_relevance_format, formattedMin, formattedMax)
            } else {
                txtDataRelevance.text = getString(R.string.data_relevance_unknown)
            }
        }

        // --- Language Spinner ---
        val spinnerLanguage: Spinner = view.findViewById(R.id.spinnerLanguage)
        val languages = listOf(
            Pair("en", getString(R.string.lang_en)),
            Pair("pl", getString(R.string.lang_pl)),
            Pair("uk", getString(R.string.lang_uk))
        )

        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, languages.map { it.second })
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerLanguage.adapter = adapter

        val currentLang = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        val index = languages.indexOfFirst { it.first == currentLang }
        if (index != -1) {
            spinnerLanguage.setSelection(index)
        }

        spinnerLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedLangTag = languages[position].first
                if (selectedLangTag != AppCompatDelegate.getApplicationLocales().toLanguageTags()) {
                    val appLocale = LocaleListCompat.forLanguageTags(selectedLangTag)
                    AppCompatDelegate.setApplicationLocales(appLocale)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // --- Map Settings ---
        val switchRedArrow = view.findViewById<SwitchMaterial>(R.id.switchRedArrow)
        switchRedArrow.isChecked = prefs.getBoolean("pref_show_red_arrow", true)
        switchRedArrow.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("pref_show_red_arrow", isChecked).apply()
        }

        // Highlight mode (RadioGroup)
        val radioGroupHighlight = view.findViewById<RadioGroup>(R.id.radioGroupHighlight)
        val currentMode = prefs.getInt("pref_highlight_mode", 1) // Default to Smart (1)
        
        when (currentMode) {
            0 -> radioGroupHighlight.check(R.id.radioHighlightSimple)
            1 -> radioGroupHighlight.check(R.id.radioHighlightSmart)
            2 -> radioGroupHighlight.check(R.id.radioHighlightNone)
        }

        radioGroupHighlight.setOnCheckedChangeListener { _, checkedId ->
            val mode = when (checkedId) {
                R.id.radioHighlightSimple -> 0
                R.id.radioHighlightSmart -> 1
                R.id.radioHighlightNone -> 2
                else -> 1
            }
            prefs.edit().putInt("pref_highlight_mode", mode).apply()
        }

        // --- Logs Button ---
        view.findViewById<Button>(R.id.btnShowLogs).setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_logs)
        }

        // --- Restore Button ---
        val btnRestore = view.findViewById<Button>(R.id.btnRestore)
        if (hasBackup()) {
            btnRestore.visibility = View.VISIBLE
            btnRestore.setOnClickListener {
                showRestoreDialog()
            }
        } else {
            btnRestore.visibility = View.GONE
        }

        // --- Agencies RecyclerView ---
        val recyclerAgencies: RecyclerView = view.findViewById(R.id.recyclerAgencies)
        recyclerAgencies.layoutManager = LinearLayoutManager(requireContext())
        val agencyAdapter = AgencyAdapter(emptyList()) { agency, isChecked ->
            vm.updateAgency(agency, isChecked)
        }
        recyclerAgencies.adapter = agencyAdapter

        vm.agencies.observe(viewLifecycleOwner) { list ->
            agencyAdapter.update(list)
        }
    }

    private fun hasBackup(): Boolean {
        val dbFile = requireContext().getDatabasePath("gtfs_database")
        val backupFile = File(dbFile.parent, "gtfs_database.bak")
        return backupFile.exists()
    }

    private fun showRestoreDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.restore_title))
            .setMessage(getString(R.string.restore_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                restoreDatabase()
            }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    private fun restoreDatabase() {
        try {
            val context = requireContext()
            val dbFile = context.getDatabasePath("gtfs_database")
            val backupFile = File(dbFile.parent, "gtfs_database.bak")

            if (backupFile.exists()) {
                GtfsDatabase.getInstance(context).close() // Close DB connection
                backupFile.copyTo(dbFile, overwrite = true)
                
                Toast.makeText(context, getString(R.string.restore_success), Toast.LENGTH_LONG).show()
                
                // Restart app
                val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
                if (intent != null) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    exitProcess(0)
                }
            }
        } catch (e: Exception) {
            AppLogger.logError(requireContext(), "Failed to restore database", e)
            Toast.makeText(requireContext(), getString(R.string.restore_failed), Toast.LENGTH_SHORT).show()
        }
    }
}

class SettingsViewModelFactory(private val dao: gtfsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
