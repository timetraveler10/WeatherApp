package com.example.weatherapp.data.remote.dto.daily


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyForecastDto(
    @SerialName("forecast")
    val forecast: List<Forecast>
)