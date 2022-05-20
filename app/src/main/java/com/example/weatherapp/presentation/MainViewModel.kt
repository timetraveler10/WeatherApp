package com.example.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.local.DatastoreRepo
import com.example.weatherapp.data.local.LocationEntity
import com.example.weatherapp.domain.model.DailyWeather
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.use_cases.GetDailyWeatherUseCase
import com.example.weatherapp.domain.use_cases.GetWeatherUseCase
import com.example.weatherapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(
    val getWeatherUseCase: GetWeatherUseCase,
    val dailyWeatherUseCase: GetDailyWeatherUseCase,
    private val datastoreRepo: DatastoreRepo
) : ViewModel() {

    var state by mutableStateOf(WeatherState<Weather>())

    var dailyWeatherState by mutableStateOf(WeatherState<List<DailyWeather>>())


    init {
        onEvent(WeatherEvents.GetWeather)
    }

    fun onEvent(weatherEvents: WeatherEvents) {
        when (weatherEvents) {
            is WeatherEvents.GetWeather -> {
                viewModelScope.launch {
                    getWeather()
                }
            }
            is WeatherEvents.GetDailyWeather -> {
                viewModelScope.launch {
                    getDailyWeather()
                }
            }
            is WeatherEvents.PersistLocation -> {
                viewModelScope.launch {
                    datastoreRepo.persistLatLng(
                        lat = weatherEvents.locationEntity.lat,
                        lng = weatherEvents.locationEntity.lng
                    )
                }
            }
        }
    }

    private fun getWeather() {
        getWeatherUseCase().onEach {
            state = when (it) {
                is Resource.Success -> {
                    state.copy(
                        weather = it.data,
                        isLoading = false,
                        locationEntity = it.data!!.locationEntity
                    )
                }
                is Resource.Error -> {
                    state.copy(message = it.message, isLoading = false)
                }
                is Resource.Loading -> {
                    state.copy(isLoading = true)
                }

            }

        }.launchIn(viewModelScope)
    }

    private suspend fun getDailyWeather() {
        dailyWeatherUseCase().collectLatest {
            dailyWeatherState = when (it) {
                is Resource.Success -> {
                    dailyWeatherState.copy(weather = it.data!!, isLoading = false)
                }
                is Resource.Error -> {
                    dailyWeatherState.copy(message = it.message)
                }
                is Resource.Loading -> {
                    dailyWeatherState.copy(isLoading = true)
                }

            }

        }
    }

}
