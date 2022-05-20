package com.example.weatherapp.presentation

import com.example.weatherapp.data.local.LocationEntity

data class WeatherState<T>(
    val weather: T? = null,
    val isLoading: Boolean = false,
    val message: String? = null,
    val locationEntity: LocationEntity = LocationEntity(0.0, 0.0)

)
