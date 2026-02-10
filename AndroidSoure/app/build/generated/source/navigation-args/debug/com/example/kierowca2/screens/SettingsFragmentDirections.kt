package com.example.kierowca2.screens

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.kierowca2.R

public class SettingsFragmentDirections private constructor() {
  public companion object {
    public fun actionSettingsToLogs(): NavDirections =
        ActionOnlyNavDirections(R.id.action_settings_to_logs)
  }
}
