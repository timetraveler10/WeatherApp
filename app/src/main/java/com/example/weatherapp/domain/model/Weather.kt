package com.example.weatherapp.domain.model

import com.example.weatherapp.data.local.LocationEntity

data class Weather(
    val current: CurrentWeather,
    val hourlyWeather: List<HourlyWeather>,
    val locationEntity: LocationEntity = LocationEntity(0.0, 0.0)
)
