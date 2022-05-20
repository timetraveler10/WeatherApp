package com.example.weatherapp.data.remote.dto.current


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("current")
    val current: Current
)