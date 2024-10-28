package com.example.myapp009adatastore

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreViewModel(application: Application) : ViewModel() {
    private val dataStore = application.applicationContext.dataStore

    private val nameKey = stringPreferencesKey("name")
    private val ageKey = stringPreferencesKey("age")

    fun saveData(name: String, age: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[nameKey] = name
                preferences[ageKey] = age
            }
        }
    }

    fun readData(): Flow<Pair<String, String>> {
        return dataStore.data.map { preferences ->
            // Pokud klíče neexistují, vrátí se výchozí hodnoty
            val name = preferences[nameKey] ?: "Neznámé"
            val age = preferences[ageKey] ?: "Neznámý"
            Pair(name, age)
        }
    }
}
