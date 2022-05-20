package com.example.weatherapp.presentation

import com.example.weatherapp.data.local.LocationEntity

sealed class WeatherEvents{
    object GetWeather:WeatherEvents()
    object GetDailyWeather:WeatherEvents()
    data class PersistLocation(val locationEntity: LocationEntity):WeatherEvents()

}
