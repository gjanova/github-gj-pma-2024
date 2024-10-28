package com.example.myapp009adatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

// Vytvoření rozšíření pro DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
