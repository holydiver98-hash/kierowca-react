package com.example.kierowca2.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kierowca2.R
import com.example.kierowca2.data.GtfsDatabase
import com.example.kierowca2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(GtfsDatabase.getInstance(this).gtfsDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
        setSupportActionBar(toolbar)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        val navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        observeDataRelevance()
    }

    private fun observeDataRelevance() {
        mainViewModel.isDataOutdated.observe(this) { isOutdated ->
            if (isOutdated) {
                showUpdateDialog()
            }
        }
    }

    private fun showUpdateDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.data_outdated_title))
            .setMessage(getString(R.string.data_outdated_message))
            .setPositiveButton(getString(R.string.update)) { _, _ ->
                findNavController(R.id.nav_host).navigate(R.id.importFragment)
            }
            .setNegativeButton(getString(R.string.later), null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
