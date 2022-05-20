package com.example.weatherapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


const val PREFERENCE_NAME = "latLng_preference"
const val Lat_PREFERENCE_KEY = "lat_key"
const val Lng_PREFERENCE_KEY = "lng_key"
const val LngLatStr_PREFERENCE_KEY = "lng_key"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(PREFERENCE_NAME)

@Singleton
class DatastoreRepo @Inject constructor(@ApplicationContext val context: Context) {

    private object PreferencesKeys {
        val lngLatStr = stringPreferencesKey(LngLatStr_PREFERENCE_KEY)
    }

    private val dataStore = context.datastore

    suspend fun persistLatLng(lat: Double, lng: Double) {

        dataStore.edit {
            it[PreferencesKeys.lngLatStr] = "${lng},${lat}"
        }

    }


    fun readLngLat(): Flow<String> {

        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferencesKeys.lngLatStr] ?: "0.0,0.0"

            }
    }
}


