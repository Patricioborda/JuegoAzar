package com.example.juegoazar.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreManager(context: Context) {
    private val appContext = context.applicationContext
    private val MAX_SCORE_KEY = intPreferencesKey("max_score")

    val maxScore: Flow<Int> = appContext.dataStore.data.map { preferences ->
        preferences[MAX_SCORE_KEY] ?: 0
    }

    suspend fun saveMaxScore(score: Int) {
        appContext.dataStore.edit { preferences ->
            preferences[MAX_SCORE_KEY] = score
        }
    }
}
