package com.example.weatherapp.data.remote.dto.hourly


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyForecastDto(
    @SerialName("forecast")
    val forecast: List<Forecast>
)